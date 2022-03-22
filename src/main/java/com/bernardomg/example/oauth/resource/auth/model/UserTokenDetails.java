
package com.bernardomg.example.oauth.resource.auth.model;

import lombok.Data;

@Data
public class UserTokenDetails {

    private String access_token;

    private Float  expires_in;

    private Float  refresh_expires_in;

    private String refresh_token;

    private String token_type;

    private String id_token;

    private String session_state;

    private String scope;

    private String error;

    private String error_description;

}
