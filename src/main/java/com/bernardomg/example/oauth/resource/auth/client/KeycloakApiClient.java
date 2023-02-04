
package com.bernardomg.example.oauth.resource.auth.client;

import com.bernardomg.example.oauth.resource.auth.model.KeycloakUser;
import com.bernardomg.example.oauth.resource.auth.model.KeycloakUserTokenDetails;

public interface KeycloakApiClient {

    public Iterable<KeycloakUser> getAllUsers();

    public String getUser(final String username);

    public KeycloakUserTokenDetails login(final String username, final String password);

    public String logout(final String username, final String refreshToken);

}
