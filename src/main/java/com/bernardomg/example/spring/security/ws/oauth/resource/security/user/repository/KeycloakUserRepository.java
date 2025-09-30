
package com.bernardomg.example.spring.security.ws.oauth.resource.security.user.repository;

import java.util.Objects;
import java.util.stream.Collectors;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;

import com.bernardomg.example.spring.security.ws.oauth.resource.security.property.OauthProperties;
import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.model.DefaultUser;
import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.model.User;

public final class KeycloakUserRepository implements UserRepository {

    private final String clientId;

    private final String password;

    private final String realm;

    private final String serverURL;

    private final String userName;

    private final String userRealm;

    public KeycloakUserRepository(final OauthProperties properties) {
        super();

        serverURL = Objects.requireNonNull(properties.server()
            .url());
        realm = Objects.requireNonNull(properties.realm());
        clientId = Objects.requireNonNull(properties.admin()
            .clientId());
        userName = Objects.requireNonNull(properties.admin()
            .username());
        password = Objects.requireNonNull(properties.admin()
            .password());
        userRealm = Objects.requireNonNull(properties.admin()
            .realm());
    }

    @Override
    public final Iterable<User> findAll() {
        Keycloak      keycloak;
        RealmResource realmResource;
        UsersResource userResource;

        keycloak = KeycloakBuilder.builder()
            .serverUrl(serverURL)
            .grantType(OAuth2Constants.PASSWORD)
            .realm(userRealm)
            .username(userName)
            .password(password)
            .clientId(clientId)
            .resteasyClient(new ResteasyClientBuilderImpl().connectionPoolSize(10)
                .build())
            .build();

        realmResource = keycloak.realm(realm);
        userResource = realmResource.users();

        return userResource.list()
            .stream()
            .map(this::toUser)
            .collect(Collectors.toList());
    }

    private final User toUser(final UserRepresentation representation) {
        final User user;

        user = new DefaultUser();
        user.setUsername(representation.getUsername());

        return user;
    }

}
