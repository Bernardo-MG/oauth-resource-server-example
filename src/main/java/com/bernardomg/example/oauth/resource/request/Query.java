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

/**
 * A query from the frontend.
 * 
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public interface Query {

    /**
     * Returns the query.
     * 
     * @return the query
     */
    public String getQuery();

    /**
     * Returns the query variables.
     * 
     * @return the query variables
     */
    public Map<String, Object> getVariables();

    /**
     * Sets the query.
     * 
     * @param query
     *            the query
     */
    public void setQuery(final String query);

    /**
     * Sets the query variables.
     * 
     * @param variables
     *            the query variables
     */
    public void setVariables(final Map<String, Object> variables);

}
