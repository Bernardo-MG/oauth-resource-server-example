
package com.bernardomg.example.spring.security.ws.oauth.resource.security.user.adapter.inbound.keycloak.repository;

import java.util.Objects;
import java.util.stream.Collectors;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;

import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.domain.model.User;
import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.domain.repository.UserRepository;

public final class KeycloakUserRepository implements UserRepository {

    private final Keycloak keycloak;

    private final String   realm;

    public KeycloakUserRepository(final Keycloak keyclk, final String rlm) {
        super();

        keycloak = Objects.requireNonNull(keyclk);
        realm = Objects.requireNonNull(rlm);
    }

    @Override
    public final Iterable<User> findAll() {
        final RealmResource realmResource;
        final UsersResource userResource;

        realmResource = keycloak.realm(realm);
        userResource = realmResource.users();

        return userResource.list()
            .stream()
            .map(this::toUser)
            .collect(Collectors.toList());
    }

    private final User toUser(final UserRepresentation representation) {
        return new User(representation.getUsername(), "");
    }

}
