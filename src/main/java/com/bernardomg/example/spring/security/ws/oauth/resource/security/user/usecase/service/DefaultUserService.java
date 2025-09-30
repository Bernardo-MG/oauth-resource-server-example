
package com.bernardomg.example.spring.security.ws.oauth.resource.security.user.usecase.service;

import java.util.Objects;

import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.domain.model.User;
import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.domain.repository.UserRepository;

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
