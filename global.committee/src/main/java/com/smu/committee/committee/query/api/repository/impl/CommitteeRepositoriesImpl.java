package com.smu.committee.committee.query.api.repository.impl;

import com.smu.committee.committee.core.model.Committee;
import com.smu.committee.committee.query.api.repository.CommitteeRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CommitteeRepositoriesImpl implements CommitteeRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<Committee> getLastElement() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "committeeCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, Committee.class)
                .next();
    }
    }

