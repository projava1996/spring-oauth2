package com.authorizationserver.service.security;

import com.authorizationserver.custom.security.OAuth2ResourceOwnerToken;
import com.authorizationserver.custom.security.Sha1PasswordEncoder;
import com.authorizationserver.repository.CmsUserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class OAuth2ResourceOwnerProvider implements AuthenticationProvider {
    private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-4.3";
    private final Log logger = LogFactory.getLog(getClass());
    private final UserDetailsService userDetailsService;
    private final RegisteredClientRepository registeredClientRepository;
    private final OAuth2AuthorizationService authorizationService;
    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
    private final CmsUserRepository cmsUserRepository;

    public OAuth2ResourceOwnerProvider(OAuth2AuthorizationService authorizationService,
                                       UserDetailsService userDetailsService,
                                       RegisteredClientRepository registeredClientRepository,
                                       OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator,
                                       PasswordEncoder passwordEncoder,
                                       CmsUserRepository userRepository) {
        Assert.notNull(registeredClientRepository, "registeredClientRepository cannot be null");
        Assert.notNull(tokenGenerator, "tokenGenerator cannot be null");
        Assert.notNull(userDetailsService, "userDetailService cannot be null");
        Assert.notNull(authorizationService, "authorizationService cannot be null");
        this.userDetailsService = userDetailsService;
        this.registeredClientRepository = registeredClientRepository;
        this.tokenGenerator = tokenGenerator;
        this.passwordEncoder = passwordEncoder;
        this.authorizationService = authorizationService;
        this.cmsUserRepository = userRepository;
    }

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OAuth2ResourceOwnerToken oAuth2ResourceOwnerToken = (OAuth2ResourceOwnerToken) authentication;
        RegisteredClient registeredClient = registeredClientRepository.findByClientId(oAuth2ResourceOwnerToken.getClientId());
        UserDetails userDetails = userDetailsService.loadUserByUsername(oAuth2ResourceOwnerToken.getUsername());
        if (registeredClient == null) {
            throwError("invalid_request", "client_id", oAuth2ResourceOwnerToken, null);
        }
        if (userDetails == null) {
            throwInvalidClient("username");
        }
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("Retrieved registered client");
        }
        String clientSecret = oAuth2ResourceOwnerToken.getClientSecret();
        if (!this.passwordEncoder.matches(clientSecret, registeredClient.getClientSecret())) {
            throwInvalidClient("client_secret");
        }
        String password = (new Sha1PasswordEncoder()).encode(oAuth2ResourceOwnerToken.getPassword());
        if (!this.passwordEncoder.matches(password, userDetails.getPassword())) {
            throwInvalidClient("password");
        }
        var authorities = cmsUserRepository.findUserAuthorityByUsername(oAuth2ResourceOwnerToken.getUsername());
        OAuth2TokenContext tokenContext = DefaultOAuth2TokenContext.builder().registeredClient(registeredClient)
                .principal(oAuth2ResourceOwnerToken)
                .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                .authorizedScopes(new HashSet<>(authorities)).tokenType(OAuth2TokenType.ACCESS_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .authorizationGrant(oAuth2ResourceOwnerToken).build();
        OAuth2Token generatedAccessToken = this.tokenGenerator.generate(tokenContext);
        if (generatedAccessToken == null) {
            OAuth2Error error = new OAuth2Error("server_error", "The token generator failed to generate the access token.", "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2");
            throw new OAuth2AuthenticationException(error);
        } else {
            if (this.logger.isTraceEnabled()) {
                this.logger.trace("Generated access token");
            }

            OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(), generatedAccessToken.getExpiresAt(), Collections.emptySet());
            OAuth2Authorization.Builder authorizationBuilder = OAuth2Authorization.withRegisteredClient(registeredClient)
                    .principalName(oAuth2ResourceOwnerToken.getClientId())
                    .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS);
            if (generatedAccessToken instanceof ClaimAccessor) {
                authorizationBuilder.token(accessToken, (metadata) -> {
                    metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, ((ClaimAccessor) generatedAccessToken).getClaims());
                });
            } else {
                authorizationBuilder.accessToken(accessToken);
            }

            OAuth2Authorization authorization = authorizationBuilder.build();
            this.authorizationService.save(authorization);
            if (this.logger.isTraceEnabled()) {
                this.logger.trace("Saved authorization");
                this.logger.trace("Authenticated token request");
            }
            return new OAuth2AccessTokenAuthenticationToken(registeredClient, oAuth2ResourceOwnerToken, accessToken);
        }
    }

    private static void throwInvalidClient(String parameterName) {
        OAuth2Error error = new OAuth2Error("invalid_client", "Client authentication failed: " + parameterName, "https://datatracker.ietf.org/doc/html/rfc6749#section-3.2.1");
        throw new OAuth2AuthenticationException(error);
    }

    private static OAuth2TokenContext createAuthorizationCodeTokenContext(OAuth2ResourceOwnerToken oAuth2ResourceOwnerToken, RegisteredClient registeredClient, OAuth2Authorization authorization, Set<String> authorizedScopes) {
        DefaultOAuth2TokenContext.Builder tokenContextBuilder = (DefaultOAuth2TokenContext.builder().registeredClient(registeredClient).principal((Authentication) oAuth2ResourceOwnerToken.getPrincipal()).authorizationServerContext(AuthorizationServerContextHolder.getContext()).tokenType(new OAuth2TokenType("code")).authorizedScopes(authorizedScopes)).authorizationGrantType(AuthorizationGrantType.PASSWORD).authorizationGrant(oAuth2ResourceOwnerToken);
        if (authorization != null) {
            tokenContextBuilder.authorization(authorization);
        }

        return tokenContextBuilder.build();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OAuth2ResourceOwnerToken.class.isAssignableFrom(authentication);
    }

    private static void throwError(String errorCode, String parameterName, OAuth2ResourceOwnerToken authorizationCodeRequestAuthentication, RegisteredClient registeredClient) {
        throwError(errorCode, parameterName, "https://datatracker.ietf.org/doc/html/rfc6749#section-4.1.2.1", authorizationCodeRequestAuthentication, registeredClient, null);
    }

    private static void throwError(String errorCode, String parameterName, String errorUri, OAuth2ResourceOwnerToken authorizationCodeRequestAuthentication, RegisteredClient registeredClient, OAuth2AuthorizationRequest authorizationRequest) {
        OAuth2Error error = new OAuth2Error(errorCode, "OAuth 2.0 Parameter: " + parameterName, errorUri);
        throwError(error, parameterName, authorizationCodeRequestAuthentication, registeredClient, authorizationRequest);
    }

    private static void throwError(OAuth2Error error, String parameterName, OAuth2ResourceOwnerToken authorizationCodeRequestAuthentication, RegisteredClient registeredClient, OAuth2AuthorizationRequest authorizationRequest) {
        throw new OAuth2AuthenticationException(error);
    }
}
