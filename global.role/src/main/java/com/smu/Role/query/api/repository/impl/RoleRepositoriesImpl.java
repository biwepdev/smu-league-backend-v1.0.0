package com.smu.Role.query.api.repository.impl;

import com.smu.Role.core.model.Role;
import com.smu.Role.query.api.repository.RoleRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class RoleRepositoriesImpl implements RoleRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<Role> getLastElement() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "roleCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, Role.class)
                .next();
    }
}
