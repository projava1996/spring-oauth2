package com.authorizationserver.config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.authorizationserver.custom.security.CustomJwtGrantedAuthoritiesConverter;
import com.authorizationserver.custom.security.JwtAuthenticationEntryPoint;
import com.authorizationserver.custom.security.OAuth2ResourceOwnerPasswordConverter;
import com.authorizationserver.repository.CmsUserRepository;
import com.authorizationserver.service.security.OAuth2ResourceOwnerProvider;
import com.authorizationserver.utils.Jwks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtGenerator;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collection;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService,
                                                   RegisteredClientRepository registeredClientRepository,
                                                   OAuth2AuthorizationService authorizationService,
                                                   OAuth2TokenGenerator<?> tokenGenerator,
                                                   PasswordEncoder passwordEncoder,
                                                   CmsUserRepository cmsUserRepository) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();
        authorizationServerConfigurer
                .tokenEndpoint(tokenEndpoint ->
                        tokenEndpoint
                                .accessTokenRequestConverter(
                                        new OAuth2ResourceOwnerPasswordConverter())
                                .authenticationProvider(
                                        new OAuth2ResourceOwnerProvider(
                                                authorizationService, userDetailsService, registeredClientRepository,
                                                tokenGenerator, passwordEncoder, cmsUserRepository)));

        RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
        http.requestMatcher(endpointsMatcher).csrf().disable()
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .apply(authorizationServerConfigurer);
//        http.authorizeHttpRequests(c -> c.mvcMatchers("/api/transaction/download/**").permitAll()
//                        .mvcMatchers("/api/**").authenticated()
//                )
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .csrf().disable().apply(authorizationServerConfigurer);
//        http.authorizeHttpRequests(c -> c.requestMatchers(new AntPathRequestMatcher("/api/transaction/download/**"))
//                        .permitAll())
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt).csrf().disable();

        return http.exceptionHandling(ex -> ex
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        ).build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests().anyRequest().authenticated().and().oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt).csrf().disable();
        return http.build();
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        try {
            ClassPathResource publicKey = new ClassPathResource("key.pub");
            ClassPathResource privateKey = new ClassPathResource("key.priv");
            RSAKey rsaKey = Jwks.buildKey(Jwks.loadPrivateKey(privateKey.getInputStream()), Jwks.loadPublicKey(publicKey.getInputStream()));
            JWKSet jwkSet = new JWKSet(rsaKey);
            return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public OAuth2TokenGenerator tokenGenerator(JwtEncoder jwtEncoder) {
        return new JwtGenerator(jwtEncoder);

    }

    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    //
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public Converter<Jwt, Collection<GrantedAuthority>> grantedAuthoritiesConverter(CmsUserRepository userRepository) {
        Converter<Jwt, Collection<GrantedAuthority>> converter = new CustomJwtGrantedAuthoritiesConverter(userRepository);
        return converter;
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter(Converter<Jwt, Collection<GrantedAuthority>> converter) {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(converter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
