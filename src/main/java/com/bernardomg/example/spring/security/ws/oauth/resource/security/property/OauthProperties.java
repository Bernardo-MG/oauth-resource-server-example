/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2021-2023 the original author or authors.
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

package com.bernardomg.example.spring.security.ws.oauth.resource.security.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * OAuth configuration properties.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Data
@ConfigurationProperties(prefix = "security.oauth")
public final class OauthProperties {

    /**
     * Admin user properties.
     * 
     * @author Bernardo
     *
     */
    @Data
    public static final class Admin {

        /**
         * Admin client id.
         */
        private String clientId;

        /**
         * Admin password.
         */
        private String password;

        /**
         * Admin realm.
         */
        private String realm;

        /**
         * Admin username.
         */
        private String username;

    }

    /**
     * Server properties.
     * 
     * @author Bernardo
     *
     */
    @Data
    public static final class Server {

        /**
         * Server URL.
         */
        private String url;

    }

    /**
     * Admin user properties.
     */
    private Admin  admin;

    /**
     * Oauth realm.
     */
    private String realm;

    /**
     * Oauth server properties.
     */
    private Server server;

}