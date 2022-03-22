
package com.bernardomg.example.oauth.resource.user.repository;

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

import com.bernardomg.example.oauth.resource.user.model.DefaultUser;
import com.bernardomg.example.oauth.resource.user.model.User;

@Component
public final class DefaultUserRepository implements UserRepository {

    private final String serverURL;

    private final String realm;

    private final String userRealm;

    private final String userName;

    private final String password;

    private final String clientId;

    public DefaultUserRepository(@Value("${security.server.url}") String url,
            @Value("${security.realm}") String rlm,
            @Value("${security.admin.clientId}") String cltId,
            @Value("${security.admin.username}") String user,
            @Value("${security.admin.password}") String pass,
            @Value("${security.admin.realm}") String userRlm) {
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
        Keycloak keycloak;
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
