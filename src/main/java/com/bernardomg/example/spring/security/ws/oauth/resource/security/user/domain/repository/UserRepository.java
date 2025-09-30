
package com.bernardomg.example.spring.security.ws.oauth.resource.security.user.domain.repository;

import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.domain.model.User;

public interface UserRepository {

    public Iterable<User> findAll();

}
