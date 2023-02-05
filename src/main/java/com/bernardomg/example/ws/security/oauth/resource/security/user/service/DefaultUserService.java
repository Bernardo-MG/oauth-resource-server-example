
package com.bernardomg.example.ws.security.oauth.resource.security.user.service;

import java.util.Objects;

import com.bernardomg.example.ws.security.oauth.resource.security.user.model.User;
import com.bernardomg.example.ws.security.oauth.resource.security.user.repository.UserRepository;

public final class DefaultUserService implements UserService {

    private final UserRepository repository;

    public DefaultUserService(final UserRepository repo) {
        super();

        repository = Objects.requireNonNull(repo);
    }

    @Override
    public final Iterable<User> getUsers() {
        return repository.findAll();
    }

}
