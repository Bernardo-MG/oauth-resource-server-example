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

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig() {
        super();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests(authz -> authz
            // Sets authority required for GET requests
            .antMatchers(HttpMethod.GET, "/rest/**")
            .hasAuthority("read")
            // Sets authority required for POST requests
            .antMatchers(HttpMethod.POST, "/rest")
            .hasAuthority("write")
            // Actuators are always available
            .antMatchers("/actuator/**")
            .permitAll()
            // By default all requests require authentication
            .anyRequest()
            .authenticated())
            // OAUTH 2 with JWT
            .oauth2ResourceServer(oauth2 -> oauth2.jwt()
                .jwtAuthenticationConverter(scopeAuthenticationConverter()))
            // Stateless session
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * Automatically handles the scope prefix. So the application does not
     * require it on the authority name.
     * 
     * @return authentication provider set up for the scope
     */
    private final JwtAuthenticationConverter scopeAuthenticationConverter() {
        final JwtAuthenticationConverter converter;
        final JwtGrantedAuthoritiesConverter authoritiesConverter;

        converter = new JwtAuthenticationConverter();
        authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // Remove scope_ Prefix of
        authoritiesConverter.setAuthorityPrefix("");
        // Obtain permission from the field in JWT claim, and the mode is
        // obtained from the scope or SCP field
        authoritiesConverter.setAuthoritiesClaimName("scope");
        converter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return converter;
    }

}
