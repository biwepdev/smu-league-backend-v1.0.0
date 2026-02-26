package com.smu.village.village.query.api.repository.impl;

import com.smu.village.village.core.model.Village;
import com.smu.village.village.query.api.repository.VillageRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class VillageRepositoriesImpl implements VillageRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<Village> getLastElement() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "villageCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, Village.class)
                .next();
    }
}
