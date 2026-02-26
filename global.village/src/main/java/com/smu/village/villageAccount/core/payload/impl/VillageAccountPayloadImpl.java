package com.smu.village.villageAccount.core.payload.impl;

import com.smu.village.core.common.VillageCode;
import com.smu.village.villageAccount.core.payload.VillageAccountPayload;
import com.smu.village.villageAccount.query.api.repository.VillageAccountRepositories;
import com.smu.village.villageAccount.query.api.repository.VillageAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class VillageAccountPayloadImpl implements VillageAccountPayload {
    private final VillageAccountRepository repository;
    private final VillageAccountRepositories repositories;

    @Override
    public Mono<String> createCode() {
        return repository.count()
                .flatMap(aLong -> {
                    if (aLong == 0) {
                        return Mono.just("VAC000001");
                    }
                    else {
                        Mono<String> code = repositories.getLastElement()
                                .flatMap(villageAccount -> Mono.just(villageAccount.getVillageAccountCode()));
                        return VillageCode.villageAccountCode(code);
                    }
                });
    }
}
