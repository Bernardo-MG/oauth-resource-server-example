
package com.bernardomg.example.spring.security.ws.oauth.resource.security.configuration;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

public final class ScopeJwtAuthenticationConverter extends JwtAuthenticationConverter {

    public ScopeJwtAuthenticationConverter() {
        super();

        final JwtGrantedAuthoritiesConverter authoritiesConverter;

        authoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // Remove scope_ Prefix of
        authoritiesConverter.setAuthorityPrefix("");
        // Obtain permission from the field in JWT claim, and the mode is
        // obtained from the scope or SCP field
        authoritiesConverter.setAuthoritiesClaimName("scope");
        setJwtGrantedAuthoritiesConverter(authoritiesConverter);
    }

}
