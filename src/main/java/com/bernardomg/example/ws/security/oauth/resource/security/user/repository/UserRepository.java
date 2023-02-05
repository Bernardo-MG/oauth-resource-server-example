
package com.bernardomg.example.ws.security.oauth.resource.security.user.repository;

import com.bernardomg.example.ws.security.oauth.resource.security.user.model.User;

public interface UserRepository {

    public Iterable<User> findAll();

}
