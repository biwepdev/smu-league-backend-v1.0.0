package com.smu.village.villageAccount.query.api.repository.impl;

import com.smu.village.villageAccount.core.model.VillageAccount;
import com.smu.village.villageAccount.query.api.repository.VillageAccountRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class VillageAccountRepositoriesImpl implements VillageAccountRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<VillageAccount> getLastElement() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "villageAccountCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, VillageAccount.class)
                .next();
    }
}
