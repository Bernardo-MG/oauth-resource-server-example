
package com.bernardomg.example.spring.security.ws.oauth.resource.security.user.service;

import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.model.User;

public interface UserService {

    public Iterable<User> getUsers();

}
