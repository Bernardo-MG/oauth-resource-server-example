
package com.bernardomg.example.oauth.resource.auth.model;

import lombok.Data;

@Data
public class KeycloakUserAccess {

    private Boolean impersonate;

    private Boolean manage;

    private Boolean manageGroupMembership;

    private Boolean mapRoles;

    private Boolean view;

}
