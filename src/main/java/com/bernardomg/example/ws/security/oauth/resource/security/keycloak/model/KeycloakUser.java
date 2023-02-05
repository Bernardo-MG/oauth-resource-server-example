
package com.bernardomg.example.ws.security.oauth.resource.security.keycloak.model;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;

@Data
public class KeycloakUser {

    private KeycloakUserAccess access;

    private Collection<String> disableableCredentialTypes = new ArrayList<>();

    private String             email;

    private Boolean            emailVerified;

    private Boolean            enabled;

    private String             firstName;

    private String             id;

    private String             lastName;

    private Integer            notBefore;

    private Collection<String> requiredActions            = new ArrayList<>();

    private Boolean            totp;

    private String             username;

}
