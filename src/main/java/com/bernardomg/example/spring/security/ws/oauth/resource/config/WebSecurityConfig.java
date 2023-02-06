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

package com.bernardomg.example.spring.security.ws.oauth.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.bernardomg.example.spring.security.ws.oauth.resource.security.entrypoint.ErrorResponseAuthenticationEntryPoint;

/**
 * Web security configuration.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Configuration
public class WebSecurityConfig {

    public WebSecurityConfig() {
        super();
    }

    /**
     * Web security filter chain. Sets up all the authentication requirements for requests.
     *
     * @param http
     *            HTTP security component
     * @return web security filter chain with all authentication requirements
     * @throws Exception
     *             if the setup fails
     */
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        final Customizer<ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry> authorizeRequestsCustomizer;
        final Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>>                                      oauth2ResourceServerCustomizer;
        final Customizer<FormLoginConfigurer<HttpSecurity>>                                                 formLoginCustomizer;
        final Customizer<LogoutConfigurer<HttpSecurity>>                                                    logoutCustomizer;

        // Request authorisations
        authorizeRequestsCustomizer = getAuthorizeRequestsCustomizer();

        // Login form
        // Disabled
        formLoginCustomizer = c -> c.disable();

        // Logout form
        // Disabled
        logoutCustomizer = c -> c.disable();

        oauth2ResourceServerCustomizer = oauth2 -> oauth2.jwt()
            .jwtAuthenticationConverter(scopeAuthenticationConverter());

        http.anonymous()
            .and()
            .authorizeRequests(authorizeRequestsCustomizer)
            // OAUTH 2 with JWT
            .oauth2ResourceServer(oauth2ResourceServerCustomizer)
            // Login / logout
            .formLogin(formLoginCustomizer)
            .logout(logoutCustomizer)
            // Stateless session
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    /**
     * Returns the request authorisation configuration.
     *
     * @return the request authorisation configuration
     */
    private final Customizer<ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry>
            getAuthorizeRequestsCustomizer() {
        return c -> {
            try {
                c
                    // Actuators are always available
                    .antMatchers("/actuator/**", "/auth/login")
                    .permitAll()
                    // Sets authority required for GET requests
                    .antMatchers(HttpMethod.GET, "/rest/**")
                    .hasAuthority("read")
                    // Sets authority required for POST requests
                    .antMatchers(HttpMethod.POST, "/rest/**")
                    .hasAuthority("write")
                    // By default all requests require authentication
                    .antMatchers("/rest/**")
                    .authenticated()
                    // Authentication error handling
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(new ErrorResponseAuthenticationEntryPoint())
                    // Stateless
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
            } catch (Exception e) {
                // TODO Handle exception
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Automatically handles the scope prefix. So the application does not require it on the authority name.
     *
     * @return authentication provider set up for the scope
     */
    private final JwtAuthenticationConverter scopeAuthenticationConverter() {
        final JwtAuthenticationConverter     converter;
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
