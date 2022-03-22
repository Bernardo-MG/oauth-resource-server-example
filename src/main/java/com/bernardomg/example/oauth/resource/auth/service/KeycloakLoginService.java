
package com.bernardomg.example.oauth.resource.auth.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.bernardomg.example.oauth.resource.auth.model.UserTokenDetails;
import com.bernardomg.example.oauth.resource.user.model.DefaultUser;
import com.bernardomg.example.oauth.resource.user.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class KeycloakLoginService implements LoginService {

    private final String clientId;

    private final String loginEndpoint;

    public KeycloakLoginService(String clientId, String loginEndpoint) {
        super();

        this.clientId = clientId;
        this.loginEndpoint = loginEndpoint;
    }

    @Override
    public final User login(final String username, final String password) {
        final UserTokenDetails token;
        final DefaultUser user;

        token = endpointLogin(username, password);

        if (Strings.isBlank(token.getError())) {
            user = new DefaultUser();
            user.setUsername(username);
        } else {
            log.warn("No valid login for {}", username);
            user = new DefaultUser();
        }

        return user;
    }

    private final UserTokenDetails endpointLogin(final String username,
            final String password) {
        final RestTemplate restTemplate;
        final HttpEntity<MultiValueMap<String, String>> request;
        final MultiValueMap<String, String> map;

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
        return restTemplate.postForObject(loginEndpoint, request,
            UserTokenDetails.class);
    }

    @Override
    public final User logout(final String username) {
        final DefaultUser user;

        log.debug("Logging out {}", username);

        user = new DefaultUser();
        user.setUsername(username);

        return user;
    }

}
