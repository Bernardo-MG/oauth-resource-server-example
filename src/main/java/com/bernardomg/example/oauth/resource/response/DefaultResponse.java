/**
 * Copyright 2020 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
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
