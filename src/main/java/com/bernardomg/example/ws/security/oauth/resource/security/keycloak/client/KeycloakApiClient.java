
package com.bernardomg.example.ws.security.oauth.resource.security.keycloak.client;

import com.bernardomg.example.ws.security.oauth.resource.security.keycloak.model.KeycloakUser;
import com.bernardomg.example.ws.security.oauth.resource.security.keycloak.model.KeycloakUserTokenDetails;

public interface KeycloakApiClient {

    public Iterable<KeycloakUser> getAllUsers();

    public String getUser(final String username);

    public KeycloakUserTokenDetails login(final String username, final String password);

    public String logout(final String username, final String refreshToken);

}
