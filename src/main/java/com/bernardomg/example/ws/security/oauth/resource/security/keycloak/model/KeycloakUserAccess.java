
package com.bernardomg.example.ws.security.oauth.resource.security.keycloak.model;

import lombok.Data;

@Data
public class KeycloakUserAccess {

    private Boolean impersonate;

    private Boolean manage;

    private Boolean manageGroupMembership;

    private Boolean mapRoles;

    private Boolean view;

}
