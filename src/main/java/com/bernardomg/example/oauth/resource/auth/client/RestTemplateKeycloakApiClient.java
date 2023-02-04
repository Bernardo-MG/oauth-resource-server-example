
package com.bernardomg.example.oauth.resource.auth.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.bernardomg.example.oauth.resource.auth.model.KeycloakUser;
import com.bernardomg.example.oauth.resource.auth.model.KeycloakUserTokenDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class RestTemplateKeycloakApiClient implements KeycloakApiClient {

    private final String              adminClientId;

    private final String              adminLoginEndpoint;

    private final String              adminPassword;

    private final String              adminUsername;

    private final String              clientId;

    private final String              infoEndpoint;

    private final String              loginEndpoint;

    private final String              logoutEndpoint;

    private final Map<String, String> tokens = new HashMap<>();

    private final String              usersEndpoint;

    public RestTemplateKeycloakApiClient(final String adminCltId, final String adminUser, final String adminPass,
            final String adminRlm, final String cltId, final String endpoint, final String realm) {
        super();

        Objects.requireNonNull(endpoint);

        adminClientId = Objects.requireNonNull(adminCltId);
        adminUsername = Objects.requireNonNull(adminUser);
        adminPassword = Objects.requireNonNull(adminPass);
        adminLoginEndpoint = String.format("%s/auth/realms/%s/protocol/openid-connect/token", endpoint, adminRlm);

        clientId = Objects.requireNonNull(cltId);

        loginEndpoint = String.format("%s/auth/realms/%s/protocol/openid-connect/token", endpoint, realm);
        logoutEndpoint = String.format("%s/auth/realms/%s/protocol/openid-connect/logout", endpoint, realm);
        infoEndpoint = String.format("%s/auth/realms/%s/protocol/openid-connect/userinfo", endpoint, realm);
        usersEndpoint = String.format("%s/auth/admin/realms/%s/users", endpoint, realm);
    }

    @Override
    public final Iterable<KeycloakUser> getAllUsers() {
        final String                        token;
        final MultiValueMap<String, String> headers;
        final KeycloakUser[]                users;

        token = getAdminToken();

        headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Bearer " + token);
        headers.add("cache-control", "no-cache");

        users = get(usersEndpoint, null, headers, KeycloakUser[].class).orElse(new KeycloakUser[0]);

        return Arrays.asList(users);
    }

    @Override
    public final String getUser(final String username) {
        final String                        token;
        final MultiValueMap<String, String> headers;

        Objects.requireNonNull(username);

        if (tokens.containsKey(username)) {
            token = tokens.get(username);
        } else {
            token = "";
        }

        headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", "Bearer " + token);

        return post(infoEndpoint, null, headers, String.class).orElse("");
    }

    @Override
    public final KeycloakUserTokenDetails login(final String username, final String password) {
        final MultiValueMap<String, String>      map;
        final Optional<KeycloakUserTokenDetails> details;

        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("grant_type", "password");
        map.add("scope", "openid");
        map.add("username", username);
        map.add("password", password);

        details = post(loginEndpoint, map, null, KeycloakUserTokenDetails.class);

        if (details.isPresent()) {
            tokens.put(username, details.get()
                .getAccess_token());
        }

        return details.orElse(null);
    }

    @Override
    public final String logout(final String username, final String refreshToken) {
        final MultiValueMap<String, String> map;

        Objects.requireNonNull(username);
        Objects.requireNonNull(refreshToken);

        map = new LinkedMultiValueMap<>();
        map.add("client_id", clientId);
        map.add("username", username);
        map.add("refresh_token", username);

        return post(logoutEndpoint, map, null, String.class).orElse("");
    }

    private final <T> Optional<T> get(final String url, final MultiValueMap<String, String> body,
            final MultiValueMap<String, String> headers, final Class<T> cls) {
        final RestTemplate                              restTemplate;
        final HttpEntity<MultiValueMap<String, String>> request;
        final T                                         response;
        Optional<T>                                     result;

        log.debug("Get request to {}", url);
        log.debug("Request body: {}", body);
        log.debug("Request headers: {}", headers);

        restTemplate = new RestTemplate();
        request = new HttpEntity<>(body, headers);

        try {
            response = restTemplate.exchange(url, HttpMethod.GET, request, cls)
                .getBody();
            result = Optional.of(response);
            log.debug("Request response: {}", response);
        } catch (final Exception e) {
            log.error(e.getLocalizedMessage());
            result = Optional.empty();
        }

        return result;
    }

    private final String getAdminToken() {
        final MultiValueMap<String, String> map;
        final KeycloakUserTokenDetails      details;
        final MultiValueMap<String, String> headers;

        map = new LinkedMultiValueMap<>();
        map.add("client_id", adminClientId);
        map.add("grant_type", "password");
        map.add("scope", "openid");
        map.add("username", adminUsername);
        map.add("password", adminPassword);

        headers = new LinkedMultiValueMap<>();
        headers.add("cache-control", "no-cache");

        log.debug("Calling login endpoint: {}", adminLoginEndpoint);
        log.debug("Using parameters: {}", map);

        details = post(adminLoginEndpoint, map, headers, KeycloakUserTokenDetails.class)
            .orElseGet(KeycloakUserTokenDetails::new);

        return details.getAccess_token();
    }

    private final <T> Optional<T> post(final String url, final MultiValueMap<String, String> body,
            final MultiValueMap<String, String> headers, final Class<T> cls) {
        final RestTemplate                              restTemplate;
        final HttpEntity<MultiValueMap<String, String>> request;
        final T                                         response;
        Optional<T>                                     result;

        log.debug("Post request to {}", url);
        log.debug("Request body: {}", body);
        log.debug("Request headers: {}", headers);

        restTemplate = new RestTemplate();
        request = new HttpEntity<>(body, headers);
        try {
            response = restTemplate.postForObject(url, request, cls);
            result = Optional.of(response);
            log.debug("Request response: {}", response);
        } catch (final Exception e) {
            log.error(e.getLocalizedMessage());
            result = Optional.empty();
        }

        return result;
    }

}
