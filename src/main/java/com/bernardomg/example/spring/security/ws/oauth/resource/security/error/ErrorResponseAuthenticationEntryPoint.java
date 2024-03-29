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

package com.bernardomg.example.spring.security.ws.oauth.resource.security.error;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.bernardomg.example.spring.security.ws.oauth.resource.mvc.error.model.Error;
import com.bernardomg.example.spring.security.ws.oauth.resource.mvc.response.model.ErrorResponse;
import com.bernardomg.example.spring.security.ws.oauth.resource.mvc.response.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Authentication entry point for authentication failures. Returns an {@link ErrorResponse} for an unauthorized error.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Slf4j
public final class ErrorResponseAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Default constructor.
     */
    public ErrorResponseAuthenticationEntryPoint() {
        super();
    }

    @Override
    public final void commence(final HttpServletRequest request, final HttpServletResponse response,
            final AuthenticationException authException) throws IOException, ServletException {
        final ErrorResponse resp;
        final Error         error;
        final ObjectMapper  mapper;
        final String        serverUrl;
        final String        clientUrl;

        serverUrl = getServerUrl(request);
        clientUrl = getClientUrl(request);

        log.debug("Authentication failure for {} {} from {}: {}", request.getMethod(), serverUrl, clientUrl,
            authException.getLocalizedMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        error = Error.of("Unauthorized");
        resp = Response.error(error);

        mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), resp);
    }

    private final String getClientUrl(final HttpServletRequest request) {
        final StringBuilder serverAddressBuilder;

        serverAddressBuilder = new StringBuilder();
        serverAddressBuilder.append(request.getRemoteHost());
        serverAddressBuilder.append(":");
        serverAddressBuilder.append(request.getRemotePort());

        return serverAddressBuilder.toString();
    }

    private final String getServerUrl(final HttpServletRequest request) {
        final StringBuilder serverAddressBuilder;

        serverAddressBuilder = new StringBuilder();
        serverAddressBuilder.append(request.getScheme());
        serverAddressBuilder.append(":");
        serverAddressBuilder.append(request.getLocalAddr());
        serverAddressBuilder.append(request.getContextPath());
        serverAddressBuilder.append(":");
        serverAddressBuilder.append(request.getLocalPort());
        serverAddressBuilder.append(request.getServletPath());

        return serverAddressBuilder.toString();
    }

}
