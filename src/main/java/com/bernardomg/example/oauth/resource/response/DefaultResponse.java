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

package com.bernardomg.example.oauth.resource.response;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;

/**
 * Default implementation of the response.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 *
 * @param <T>
 *            response content type
 */
public class DefaultResponse<T> implements Response<T> {

    /**
     * Response content.
     */
    private T              content;

    /**
     * Response status.
     */
    private ResponseStatus status = ResponseStatus.SUCCESS;

    /**
     * Default constructor.
     */
    public DefaultResponse() {
        super();
    }

    /**
     * Constructs a response with the specified content.
     * 
     * @param cont
     *            content
     */
    public DefaultResponse(final T cont) {
        super();

        content = checkNotNull(cont, "Missing content");
    }

    /**
     * Constructs a response with the specified content and status.
     * 
     * @param cont
     *            content
     * @param stat
     *            status
     */
    public DefaultResponse(final T cont, final ResponseStatus stat) {
        super();

        content = checkNotNull(cont, "Missing content");
        status = checkNotNull(stat, "Missing status");
    }

    @Override
    public T getContent() {
        return content;
    }

    @Override
    public ResponseStatus getStatus() {
        return status;
    }

    /**
     * Sets the content.
     * 
     * @param value
     *            response content
     */
    public void setContent(final T value) {
        content = value;
    }

    /**
     * Sets the status.
     * 
     * @param value
     *            response status
     */
    public void setStatus(final ResponseStatus value) {
        status = value;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("status", status)
                .add("content", content).toString();
    }

}
