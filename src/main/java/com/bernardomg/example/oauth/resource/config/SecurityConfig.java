
package com.bernardomg.example.oauth.resource.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-uri}")
    private String introspectionUri;

    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-secret}")
    private String clientSecret;

    public SecurityConfig() {
        super();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests(authz -> authz
                .antMatchers(HttpMethod.GET, "/rest/**")
                .hasAuthority("SCOPE_read")
                .antMatchers(HttpMethod.POST, "/rest")
                .hasAuthority("SCOPE_write").anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken(
                        token -> token.introspectionUri(this.introspectionUri)
                                .introspectionClientCredentials(this.clientId,
                                        this.clientSecret)));
    }

}
