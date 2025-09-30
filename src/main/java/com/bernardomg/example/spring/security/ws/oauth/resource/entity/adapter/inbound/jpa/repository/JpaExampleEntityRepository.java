
package com.bernardomg.example.spring.security.ws.oauth.resource.entity.adapter.inbound.jpa.repository;

import java.util.Collection;

import com.bernardomg.example.spring.security.ws.oauth.resource.entity.adapter.inbound.jpa.model.PersistentExampleEntity;
import com.bernardomg.example.spring.security.ws.oauth.resource.entity.domain.model.ExampleEntity;
import com.bernardomg.example.spring.security.ws.oauth.resource.entity.domain.repository.ExampleEntityRepository;

public final class JpaExampleEntityRepository implements ExampleEntityRepository {

    private final ExampleEntitySpringRepository springRepository;

    public JpaExampleEntityRepository(final ExampleEntitySpringRepository repository) {
        super();

        springRepository = repository;
    }

    @Override
    public final Collection<ExampleEntity> findAll() {
        return springRepository.findAll()
            .stream()
            .map(this::toDomain)
            .toList();
    }

    private final ExampleEntity toDomain(final PersistentExampleEntity entity) {
        return new ExampleEntity(entity.getName());
    }

}
