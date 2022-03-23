
package com.bernardomg.example.oauth.resource.auth.client;

import com.bernardomg.example.oauth.resource.auth.model.UserTokenDetails;

public interface KeycloakApiClient {

    public String getUser(final String username);

    public UserTokenDetails login(final String username, final String password);

    public String logout(final String username, final String refreshToken);

}
