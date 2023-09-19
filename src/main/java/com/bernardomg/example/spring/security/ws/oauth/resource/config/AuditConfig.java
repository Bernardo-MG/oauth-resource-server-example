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

package com.bernardomg.example.spring.security.ws.oauth.resource.config;

import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bernardomg.example.spring.security.ws.oauth.resource.security.audit.AuditEventLogger;

/**
 * Audit configuration.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Configuration
public class AuditConfig {

    /**
     * Default constructor.
     */
    public AuditConfig() {
        super();
    }

    /**
     * Audit event logger.
     *
     * @return audit event logger
     */
    @Bean("auditEventLogger")
    public AuditEventLogger getAuditEventLogger() {
        return new AuditEventLogger();
    }

    /**
     * Audit event repository, to store and read audit data. As this is just an example it will be an in-memory
     * repository.
     *
     * @return audit event repository
     */
    @Bean("auditEventRepository")
    public AuditEventRepository getAuditEventRepository() {
        return new InMemoryAuditEventRepository();
    }

}
