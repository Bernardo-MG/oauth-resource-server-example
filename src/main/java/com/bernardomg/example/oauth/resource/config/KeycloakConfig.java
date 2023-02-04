/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2021 the original author or authors.
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

package com.bernardomg.example.oauth.resource.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bernardomg.example.oauth.resource.auth.client.KeycloakApiClient;
import com.bernardomg.example.oauth.resource.auth.client.RestTemplateKeycloakApiClient;

@Configuration
public class KeycloakConfig {

    public KeycloakConfig() {
        super();
    }

    @Bean("keycloakApiClient")
    public KeycloakApiClient getKeycloakApiClient(@Value("${security.admin.clientId}") final String adminCltId,
            @Value("${security.admin.username}") final String adminUser,
            @Value("${security.admin.password}") final String adminPass,
            @Value("${security.admin.realm}") final String adminRlm, @Value("${security.clientId}") final String cltId,
            @Value("${security.endpoint}") final String endpoint, @Value("${security.realm}") final String realm) {
        return new RestTemplateKeycloakApiClient(adminCltId, adminUser, adminPass, adminRlm, cltId, endpoint, realm);
    }

}
