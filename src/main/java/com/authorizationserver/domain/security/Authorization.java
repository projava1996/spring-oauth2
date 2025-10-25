package com.authorizationserver.domain.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "oauth_authorizations")
public class Authorization implements Serializable {
    private String id;
    private String registeredClientId;
    private String principalName;
    private String authorizationGrantType;
    private String authorizedScopes;
    private String attributes;
    private String state;
    //access token
    private String accessTokenValue;

    private Instant accessTokenIssuedAt;
    private Instant accessTokenExpiresAt;
    private String accessTokenType;
    private String accessTokenScopes;
    // refresh token
    private String refreshTokenValue;

    private Instant refreshTokenIssuedAt;
    private Instant refreshTokenExpiresAt;
    //authorization code
    private String authorizationCodeValue;

    private Instant authorizationCodeIssuedAt;
    private Instant authorizationCodeExpiresAt;


    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Column(name = "registered_client_id")
    public String getRegisteredClientId() {
        return registeredClientId;
    }

    public void setRegisteredClientId(String registeredClientId) {
        this.registeredClientId = registeredClientId;
    }
    @Column(name = "principal_name")
    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }
    @Column(name = "authorization_grant_type")
    public String getAuthorizationGrantType() {
        return authorizationGrantType;
    }

    public void setAuthorizationGrantType(String authorizationGrantType) {
        this.authorizationGrantType = authorizationGrantType;
    }
    @Column(name = "authorized_scopes")
    public String getAuthorizedScopes() {
        return authorizedScopes;
    }

    public void setAuthorizedScopes(String authorizedScopes) {
        this.authorizedScopes = authorizedScopes;
    }
    @Column(name = "attributes")
    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    @Column(length = 500)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Column(name = "access_token_value")
    public String getAccessTokenValue() {
        return accessTokenValue;
    }

    public void setAccessTokenValue(String accessTokenValue) {
        this.accessTokenValue = accessTokenValue;
    }
    @Column(name = "access_token_issued_at")
    public Instant getAccessTokenIssuedAt() {
        return accessTokenIssuedAt;
    }

    public void setAccessTokenIssuedAt(Instant accessTokenIssuedAt) {
        this.accessTokenIssuedAt = accessTokenIssuedAt;
    }
    @Column(name = "access_token_expires_at")
    public Instant getAccessTokenExpiresAt() {
        return accessTokenExpiresAt;
    }

    public void setAccessTokenExpiresAt(Instant accessTokenExpiresAt) {
        this.accessTokenExpiresAt = accessTokenExpiresAt;
    }
    @Column(name = "access_token_type")
    public String getAccessTokenType() {
        return accessTokenType;
    }

    public void setAccessTokenType(String accessTokenType) {
        this.accessTokenType = accessTokenType;
    }
    @Column(name = "access_token_scopes")
    public String getAccessTokenScopes() {
        return accessTokenScopes;
    }

    public void setAccessTokenScopes(String accessTokenScopes) {
        this.accessTokenScopes = accessTokenScopes;
    }
    @Column(name = "refresh_token_value")
    public String getRefreshTokenValue() {
        return refreshTokenValue;
    }

    public void setRefreshTokenValue(String refreshTokenValue) {
        this.refreshTokenValue = refreshTokenValue;
    }
    @Column(name = "refresh_token_issued_at")
    public Instant getRefreshTokenIssuedAt() {
        return refreshTokenIssuedAt;
    }

    public void setRefreshTokenIssuedAt(Instant refreshTokenIssuedAt) {
        this.refreshTokenIssuedAt = refreshTokenIssuedAt;
    }
    @Column(name = "refresh_token_expires_at")
    public Instant getRefreshTokenExpiresAt() {
        return refreshTokenExpiresAt;
    }

    public void setRefreshTokenExpiresAt(Instant refreshTokenExpiresAt) {
        this.refreshTokenExpiresAt = refreshTokenExpiresAt;
    }
    @Column(name = "authorization_code_value")
    public String getAuthorizationCodeValue() {
        return authorizationCodeValue;
    }

    public void setAuthorizationCodeValue(String authorizationCodeValue) {
        this.authorizationCodeValue = authorizationCodeValue;
    }
    @Column(name = "authorization_code_issued_at")
    public Instant getAuthorizationCodeIssuedAt() {
        return authorizationCodeIssuedAt;
    }

    public void setAuthorizationCodeIssuedAt(Instant authorizationCodeIssuedAt) {
        this.authorizationCodeIssuedAt = authorizationCodeIssuedAt;
    }
    @Column(name = "authorization_code_expires_at")
    public Instant getAuthorizationCodeExpiresAt() {
        return authorizationCodeExpiresAt;
    }

    public void setAuthorizationCodeExpiresAt(Instant authorizationCodeExpiresAt) {
        this.authorizationCodeExpiresAt = authorizationCodeExpiresAt;
    }
}
