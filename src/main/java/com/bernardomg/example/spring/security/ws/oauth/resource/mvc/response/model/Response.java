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

package com.bernardomg.example.spring.security.ws.oauth.resource.mvc.response.model;

import java.util.Arrays;
import java.util.Collection;

import com.bernardomg.example.spring.security.ws.oauth.resource.mvc.error.model.Error;

/**
 * Response to the frontend.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 * @param <T>
 *            response content type
 */
public interface Response<T> {

    /**
     * Creates an empty response.
     *
     * @param <T>
     *            response content type
     * @return an empty response
     */
    public static <T> Response<T> empty() {
        return new ImmutableResponse<>();
    }

    /**
     * Creates an error response.
     *
     * @param failures
     *            failures which caused the error
     * @return an error response
     */
    public static ErrorResponse error(final Collection<? extends Error> failures) {
        return new ImmutableErrorResponse(failures);
    }

    /**
     * Creates an error response.
     *
     * @param failure
     *            failure which caused the error
     * @return an error response
     */
    public static ErrorResponse error(final Error failure) {
        return new ImmutableErrorResponse(Arrays.asList(failure));
    }

    /**
     * Creates a response with the specified content.
     *
     * @param <T>
     *            response content type
     * @param content
     *            response content
     * @return response with the received content
     */
    public static <T> Response<T> of(final T content) {
        return new ImmutableResponse<>(content);
    }

    /**
     * Returns the response content.
     *
     * @return the response content
     */
    public T getContent();

}
