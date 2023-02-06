
package com.bernardomg.example.spring.security.ws.oauth.resource.security.user.repository;

import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.model.User;

public interface UserRepository {

    public Iterable<User> findAll();

}
