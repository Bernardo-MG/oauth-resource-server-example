
package com.bernardomg.example.spring.security.ws.oauth.resource.mvc.response.controller;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.bernardomg.example.spring.security.ws.oauth.resource.mvc.response.model.Response;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice("com.bernardomg.example.oauth.resource")
@Slf4j
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    public ResponseAdvice() {
        super();
    }

    @Override
    public Object beforeBodyWrite(final Object body, final MethodParameter returnType,
            final MediaType selectedContentType, final Class<? extends HttpMessageConverter<?>> selectedConverterType,
            final ServerHttpRequest request, final ServerHttpResponse response) {
        final Object result;

        log.trace("Received {} as response body", body);
        if (body instanceof ResponseEntity<?>) {
            // Avoid wrapping Spring responses
            result = body;
        } else if (body instanceof Response) {
            // Avoid wrapping responses
            result = body;
        } else if (body == null) {
            log.debug("Received null as response body");
            result = Response.empty();
        } else {
            result = Response.of(body);
        }

        return result;
    }

    @Override
    public boolean supports(final MethodParameter returnType,
            final Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

}
