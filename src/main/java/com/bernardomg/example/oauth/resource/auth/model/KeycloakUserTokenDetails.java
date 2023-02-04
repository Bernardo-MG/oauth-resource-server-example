
package com.bernardomg.example.oauth.resource.auth.model;

import lombok.Data;

@Data
public class KeycloakUserTokenDetails {

    private String access_token;

    private String error;

    private String error_description;

    private Float  expires_in;

    private String id_token;

    private Float  refresh_expires_in;

    private String refresh_token;

    private String scope;

    private String session_state;

    private String token_type;

}
