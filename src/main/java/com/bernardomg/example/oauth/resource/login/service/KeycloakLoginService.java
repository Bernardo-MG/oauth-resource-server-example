
package com.bernardomg.example.oauth.resource.login.service;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bernardomg.example.oauth.resource.auth.client.KeycloakApiClient;
import com.bernardomg.example.oauth.resource.auth.model.UserTokenDetails;
import com.bernardomg.example.oauth.resource.user.model.DefaultUser;
import com.bernardomg.example.oauth.resource.user.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public final class KeycloakLoginService implements LoginService {

    private final KeycloakApiClient keycloakApiClient;

    @Autowired
    public KeycloakLoginService(final KeycloakApiClient client) {
        super();

        keycloakApiClient = Objects.requireNonNull(client);
    }

    @Override
    public final User login(final String username, final String password) {
        final UserTokenDetails token;
        final DefaultUser user;

        token = keycloakApiClient.login(username, password);
        log.debug("Login response: {}", token);

        if (Strings.isBlank(token.getError())) {
            user = new DefaultUser();
            user.setUsername(username);
        } else {
            log.warn("No valid login for {}", username);
            user = new DefaultUser();
        }

        return user;
    }

    @Override
    public final User logout(final String username) {
        final String token;
        final DefaultUser user;

        log.debug("Logging out {}", username);

        token = keycloakApiClient.logout(username, username);
        log.debug("Logout response: {}", token);

        user = new DefaultUser();
        user.setUsername(username);

        return user;
    }

}
