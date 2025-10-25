package com.authorizationserver.custom.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.security.Principal;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class OAuth2ResourceOwnerToken extends AbstractAuthenticationToken {
    private String clientId;
    private String username;
    private String password;
    private String clientSecret;
    private Object credentials;
    private final Object principal;
    private RegisteredClient registeredClient;
    private Map<String, Object> additionalParameters;
    private Set<String> scopes;
    private final OAuth2AccessToken accessToken;

    public OAuth2ResourceOwnerToken(String username, String password, String clientId, String clientSecret, Object credentials, Collection<? extends GrantedAuthority> authorities, Set<String> scopes) {
        super(authorities);
        this.credentials = new Principal() {
            @Override
            public String getName() {
                return username;
            }
        };
        this.username = username;
        this.clientId = clientId;
        this.principal = this.credentials;
        this.accessToken = null;
        this.password = password;
        this.clientSecret = clientSecret;
    }

    public OAuth2ResourceOwnerToken(String clientId, String username, Object credentials, RegisteredClient registeredClient, Map<String, Object> additionalParameters, Collection<? extends GrantedAuthority> authorities, OAuth2AccessToken accessToken) {
        super(authorities);
        this.clientId = clientId;
        this.username = username;
        this.credentials = credentials;
        this.principal = new Principal() {
            @Override
            public String getName() {
                return username;
            }
        };
        this.registeredClient = registeredClient;
        this.additionalParameters = additionalParameters;
        this.accessToken = accessToken;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCredentials(Object credentials) {
        this.credentials = credentials;
    }

    public Map<String, Object> getAdditionalParameters() {
        return additionalParameters;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public String getPassword() {
        return password;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public RegisteredClient getRegisteredClient() {
        return registeredClient;
    }

    public OAuth2AccessToken getAccessToken() {
        return accessToken;
    }

    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    public void setAdditionalParameters(Map<String, Object> additionalParameters) {
        this.additionalParameters = additionalParameters;
    }
}
