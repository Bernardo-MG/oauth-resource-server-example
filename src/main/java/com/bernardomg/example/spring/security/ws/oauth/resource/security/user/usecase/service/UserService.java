
package com.bernardomg.example.spring.security.ws.oauth.resource.security.user.usecase.service;

import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.domain.model.User;

public interface UserService {

    public Iterable<User> getUsers();

}
