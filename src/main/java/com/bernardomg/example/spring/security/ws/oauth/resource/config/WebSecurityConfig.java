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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.bernardomg.example.spring.security.ws.oauth.resource.security.configuration.ScopeJwtAuthenticationConverter;
import com.bernardomg.example.spring.security.ws.oauth.resource.security.error.ErrorResponseAuthenticationEntryPoint;

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
    @Bean("webSecurityFilterChain")
    public SecurityFilterChain getWebSecurityFilterChain(final HttpSecurity http) throws Exception {

        http
            // Whitelist access
            .authorizeHttpRequests(customizer -> customizer.requestMatchers("/actuator/**")
                .permitAll())
            // Route authentication
            .authorizeHttpRequests(customizer -> customizer
                // Sets authority required for GET requests
                .requestMatchers(PathPatternRequestMatcher.withDefaults()
                    .matcher(HttpMethod.GET, "/entity/**"))
                .hasAuthority("read")
                // Sets authority required for POST requests
                .requestMatchers(PathPatternRequestMatcher.withDefaults()
                    .matcher(HttpMethod.POST, "/entity/**"))
                .hasAuthority("write")
                // By default all requests require authentication
                .requestMatchers(PathPatternRequestMatcher.withDefaults()
                    .matcher("/entity/**"))
                .authenticated())
            // OAUTH2 resource server
            .oauth2ResourceServer(
                server -> server.jwt(jwt -> jwt.jwtAuthenticationConverter(new ScopeJwtAuthenticationConverter())))
            // CORS
            .cors(customizer -> customizer.configurationSource(getCorsConfigurationSource()))
            // Authentication error handling
            .exceptionHandling(handler -> handler.authenticationEntryPoint(new ErrorResponseAuthenticationEntryPoint()))
            // Stateless
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Disable login and logout forms
            .formLogin(FormLoginConfigurer::disable)
            .logout(LogoutConfigurer::disable);

        return http.build();
    }

    private final CorsConfigurationSource getCorsConfigurationSource() {
        final CorsConfiguration               configuration;
        final UrlBasedCorsConfigurationSource source;

        configuration = new CorsConfiguration().applyPermitDefaultValues();

        source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
