
package com.bernardomg.example.ws.security.oauth.resource.security.user.repository;

import java.util.Objects;
import java.util.stream.Collectors;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bernardomg.example.ws.security.oauth.resource.security.user.model.DefaultUser;
import com.bernardomg.example.ws.security.oauth.resource.security.user.model.User;

@Component
public final class DefaultUserRepository implements UserRepository {

    private final String clientId;

    private final String password;

    private final String realm;

    private final String serverURL;

    private final String userName;

    private final String userRealm;

    public DefaultUserRepository(@Value("${security.server.url}") final String url,
            @Value("${security.realm}") final String rlm, @Value("${security.admin.clientId}") final String cltId,
            @Value("${security.admin.username}") final String user,
            @Value("${security.admin.password}") final String pass,
            @Value("${security.admin.realm}") final String userRlm) {
        super();

        serverURL = Objects.requireNonNull(url);
        realm = Objects.requireNonNull(rlm);
        clientId = Objects.requireNonNull(cltId);
        userName = Objects.requireNonNull(user);
        password = Objects.requireNonNull(pass);
        userRealm = Objects.requireNonNull(userRlm);
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
            .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10)
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
