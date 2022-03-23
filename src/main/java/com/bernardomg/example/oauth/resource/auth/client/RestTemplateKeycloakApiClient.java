
package com.bernardomg.example.oauth.resource.auth.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.bernardomg.example.oauth.resource.auth.model.UserTokenDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class RestTemplateKeycloakApiClient implements KeycloakApiClient {

    private final String              clientId;

    private final String              infoEndpoint;

    private final String              loginEndpoint;

    private final String              logoutEndpoint;

    private final Map<String, String> tokens = new HashMap<>();

    public RestTemplateKeycloakApiClient(final String cltId,
            final String endpoint, final String realm) {
        super();

        clientId = Objects.requireNonNull(cltId);

        Objects.requireNonNull(endpoint);
        loginEndpoint = endpoint + "/auth/realms/" + realm
                + "/protocol/openid-connect/token";
        logoutEndpoint = endpoint + "/auth/realms/" + realm
                + "/protocol/openid-connect/logout";
        infoEndpoint = endpoint + "/auth/realms/" + realm
                + "/protocol/openid-connect/userinfo";
    }

    @Override
    public final String getUser(final String username) {
        final RestTemplate restTemplate;
        final HttpEntity<MultiValueMap<String, String>> request;
        final String token;
        final String userData;
        final MultiValueMap<String, String> headers;

        // TODO: Rechazar clave o nombre de usuario nulo

        if (tokens.containsKey(username)) {
            token = tokens.get(username);
        } else {
            token = "";
        }

        headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Bearer " + token);

        log.debug("Calling info endpoint: {}", infoEndpoint);
        log.debug("Using headers: {}", headers);

        restTemplate = new RestTemplate();
        request = new HttpEntity<>(null, headers);
        userData = restTemplate.postForObject(infoEndpoint, request,
            String.class);

        log.debug("User info data: {}", userData);

        return userData;
    }

    @Override
    public final UserTokenDetails login(final String username,
            final String password) {
        final RestTemplate restTemplate;
        final HttpEntity<MultiValueMap<String, String>> request;
        final MultiValueMap<String, String> map;
        final UserTokenDetails details;

        // TODO: Rechazar clave o nombre de usuario nulo

        map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("grant_type", "password");
        map.add("scope", "openid");
        map.add("username", username);
        map.add("password", password);

        log.debug("Calling login endpoint: {}", loginEndpoint);
        log.debug("Using parameters: {}", map);

        restTemplate = new RestTemplate();
        request = new HttpEntity<>(map, null);
        details = restTemplate.postForObject(loginEndpoint, request,
            UserTokenDetails.class);

        tokens.put(username, details.getAccess_token());

        log.debug("Login data: {}", details);

        return details;
    }

    @Override
    public final String logout(final String username,
            final String refreshToken) {
        final RestTemplate restTemplate;
        final HttpEntity<MultiValueMap<String, String>> request;
        final MultiValueMap<String, String> map;
        final String info;

        // TODO: Rechazar clave o nombre de usuario nulo

        map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("username", username);
        map.add("refresh_token", username);

        log.debug("Calling logout endpoint: {}", logoutEndpoint);
        log.debug("Using parameters: {}", map);

        restTemplate = new RestTemplate();
        request = new HttpEntity<>(map, null);
        info = restTemplate.postForObject(logoutEndpoint, request,
            String.class);

        log.debug("Logout info: {}", info);

        return info;
    }

}
