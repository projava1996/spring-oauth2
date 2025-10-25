package com.authorizationserver.custom.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OAuth2ResourceOwnerPasswordConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!"password".equals(grantType)) {
            return null;
        }
        MultiValueMap<String, String> parameters = getFormParameters(request);
        String clientId = parameters.getFirst("client_id");
        String clientSecret = parameters.getFirst("client_secret");
        String username = parameters.getFirst("username");
        String password = parameters.getFirst("password");
        if (!StringUtils.hasLength(username)
                && StringUtils.hasLength(password)
                && StringUtils.hasLength(clientId)
                && StringUtils.hasLength(clientSecret)) {
            throw new OAuth2AuthenticationException("invalid_request");
        }
        Map<String, Object> additionalParameters = getParametersIfMatchesAuthorizationCodeGrantRequest(request, "client_id", "client_secret");
        OAuth2ResourceOwnerToken token = new OAuth2ResourceOwnerToken(username, password, clientId, clientSecret, null, null, null);
        token.setAdditionalParameters(additionalParameters);
        return token;
    }

    static MultiValueMap<String, String> getFormParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap();
        parameterMap.forEach((key, values) -> {
            String queryString = StringUtils.hasText(request.getQueryString()) ? request.getQueryString() : "";
            if (!queryString.contains(key) && values.length > 0) {
                String[] var5 = values;
                int var6 = values.length;

                for (int var7 = 0; var7 < var6; ++var7) {
                    String value = var5[var7];
                    parameters.add(key, value);
                }
            }

        });
        return parameters;
    }

    static Map<String, Object> getParametersIfMatchesAuthorizationCodeGrantRequest(HttpServletRequest request, String... exclusions) {
        if (!matchesAuthorizationCodeGrantRequest(request)) {
            return Collections.emptyMap();
        } else {
            MultiValueMap<String, String> multiValueParameters = "GET".equals(request.getMethod()) ? getQueryParameters(request) : getFormParameters(request);
            String[] var3 = exclusions;
            int var4 = exclusions.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                String exclusion = var3[var5];
                multiValueParameters.remove(exclusion);
            }

            Map<String, Object> parameters = new HashMap();
            multiValueParameters.forEach((key, value) -> {
                parameters.put(key, value.size() == 1 ? value.get(0) : value.toArray(new String[0]));
            });
            return parameters;
        }
    }

    static boolean matchesAuthorizationCodeGrantRequest(HttpServletRequest request) {
        return AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equals(request.getParameter("grant_type")) && request.getParameter("code") != null;
    }

    static boolean matchesPkceTokenRequest(HttpServletRequest request) {
        return matchesAuthorizationCodeGrantRequest(request) && request.getParameter("code_verifier") != null;
    }

    static void throwError(String errorCode, String parameterName, String errorUri) {
        OAuth2Error error = new OAuth2Error(errorCode, "OAuth 2.0 Parameter: " + parameterName, errorUri);
        throw new OAuth2AuthenticationException(error);
    }

    static MultiValueMap<String, String> getQueryParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap();
        parameterMap.forEach((key, values) -> {
            String queryString = StringUtils.hasText(request.getQueryString()) ? request.getQueryString() : "";
            if (queryString.contains(key) && values.length > 0) {
                String[] var5 = values;
                int var6 = values.length;

                for (int var7 = 0; var7 < var6; ++var7) {
                    String value = var5[var7];
                    parameters.add(key, value);
                }
            }

        });
        return parameters;
    }
}
