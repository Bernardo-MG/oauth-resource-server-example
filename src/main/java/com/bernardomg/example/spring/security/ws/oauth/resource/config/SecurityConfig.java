/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2021-2025 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.example.spring.security.ws.oauth.resource.config;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import com.bernardomg.example.spring.security.ws.oauth.resource.security.property.OauthProperties;
import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.adapter.inbound.keycloak.repository.KeycloakUserRepository;
import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.domain.repository.UserRepository;
import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.usecase.service.DefaultUserService;
import com.bernardomg.example.spring.security.ws.oauth.resource.security.user.usecase.service.UserService;

/**
 * Security configuration.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@EnableConfigurationProperties(OauthProperties.class)
public class SecurityConfig {

    /**
     * Default constructor.
     */
    public SecurityConfig() {
        super();
    }

    /**
     * Returns the admin keycloak client.
     *
     * @param properties
     *            OAuth configuration properties
     * @return the user repository
     */
    @Bean("adminKeycloak")
    public Keycloak getAdminKeycloak(final OauthProperties properties) {
        return KeycloakBuilder.builder()
            .serverUrl(properties.server()
                .url())
            .grantType(OAuth2Constants.PASSWORD)
            .realm(properties.admin()
                .realm())
            .username(properties.admin()
                .username())
            .password(properties.admin()
                .password())
            .clientId(properties.admin()
                .clientId())
            .resteasyClient(new ResteasyClientBuilderImpl().connectionPoolSize(10)
                .build())
            .build();
    }

    /**
     * Returns the user repository.
     *
     * @param keycloak
     *            Keycloak client
     * @param properties
     *            OAuth configuration properties
     * @return the user repository
     */
    @Bean("userRepository")
    public UserRepository getUserRepository(final Keycloak keycloak, final OauthProperties properties) {
        return new KeycloakUserRepository(keycloak, properties.realm());
    }

    /**
     * Returns the user service.
     *
     * @param repo
     *            user repository
     * @return the user service
     */
    @Bean("userService")
    public UserService getUserService(final UserRepository repo) {
        return new DefaultUserService(repo);
    }

}
