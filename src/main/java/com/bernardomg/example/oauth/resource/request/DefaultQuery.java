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

package com.bernardomg.example.oauth.resource.request;

import java.util.Map;

import com.google.common.base.MoreObjects;

/**
 * Default implementation of the query DTO.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public class DefaultQuery implements Query {

    /**
     * GraphQL query.
     */
    private String              query;

    /**
     * Query variables.
     */
    private Map<String, Object> variables;

    /**
     * Default constructor.
     */
    public DefaultQuery() {
        super();
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public Map<String, Object> getVariables() {
        return variables;
    }

    @Override
    public void setQuery(final String value) {
        query = value;
    }

    @Override
    public void setVariables(final Map<String, Object> value) {
        variables = value;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("query", query)
                .add("variables", variables).toString();
    }

}
