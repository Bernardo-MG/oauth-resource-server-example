
package com.bernardomg.example.oauth.resource.user.service;

import java.util.Objects;

import com.bernardomg.example.oauth.resource.user.model.User;
import com.bernardomg.example.oauth.resource.user.repository.UserRepository;

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
