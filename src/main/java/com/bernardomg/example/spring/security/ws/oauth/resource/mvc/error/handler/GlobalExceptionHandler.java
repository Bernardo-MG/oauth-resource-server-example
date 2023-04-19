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

package com.bernardomg.example.spring.security.ws.oauth.resource.mvc.error.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bernardomg.example.spring.security.ws.oauth.resource.mvc.error.model.Error;
import com.bernardomg.example.spring.security.ws.oauth.resource.mvc.response.model.ErrorResponse;
import com.bernardomg.example.spring.security.ws.oauth.resource.mvc.response.model.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * Captures and handles exceptions for all the controllers.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Default constructor.
     */
    public GlobalExceptionHandler() {
        super();
    }

    /**
     * Handles authentication and authorisation exceptions.
     *
     * @param ex
     *            exception to handle
     * @param request
     *            request
     * @return unauthorized response
     */
    @ExceptionHandler({ AuthenticationException.class, AccessDeniedException.class })
    public final ResponseEntity<Object> handleAuthException(final Exception ex, final WebRequest request) {
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles unmapped exceptions.
     *
     * @param ex
     *            exception to handle
     * @param request
     *            request
     * @return internal error response
     */
    @ExceptionHandler({ RuntimeException.class })
    public final ResponseEntity<Object> handleExceptionDefault(final Exception ex, final WebRequest request) {
        log.error(ex.getMessage(), ex);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(final Exception ex, @Nullable final Object body,
            final HttpHeaders headers, final HttpStatusCode statusCode, final WebRequest request) {
        final ErrorResponse response;
        final String        message;
        final Error         failure;

        log.error(ex.getMessage());

        if (ex.getMessage() == null) {
            message = "";
        } else {
            message = ex.getMessage();
        }

        failure = Error.of(message);
        response = Response.error(failure);

        return super.handleExceptionInternal(ex, response, headers, statusCode, request);
    }

}
