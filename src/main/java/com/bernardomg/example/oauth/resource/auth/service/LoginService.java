
package com.bernardomg.example.oauth.resource.auth.service;

import com.bernardomg.example.oauth.resource.user.model.User;

public interface LoginService {

    public User login(final String username, final String password);

    public User logout(final String username);

}
