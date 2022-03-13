
package com.bernardomg.example.oauth.resource.user.repository;

import com.bernardomg.example.oauth.resource.user.model.User;

public interface UserRepository {

    public Iterable<User> findAll();

}
