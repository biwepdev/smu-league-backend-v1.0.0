package com.smu.village.villageAccount.query.api.handler.impl;

import com.smu.village.core.query.GetByCodeQuery;
import com.smu.village.village.core.model.Village;
import com.smu.village.village.query.api.response.VillageResponse;
import com.smu.village.villageAccount.core.model.VillageAccount;
import com.smu.village.villageAccount.query.api.handler.VillageAccountQueryHandler;
import com.smu.village.villageAccount.query.api.repository.VillageAccountRepository;
import com.smu.village.villageAccount.query.api.response.VillageAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class VillageAccountQueryHandlerImpl implements VillageAccountQueryHandler {
    private final VillageAccountRepository repository;

    @Override
    public Flux<VillageAccountResponse> getVillageAccounts() {
        return repository.findAll()
                .flatMap(villageAccount -> Mono.just(getVillageAccount(villageAccount)));
    }

    @Override
    public Mono<VillageAccountResponse> getByCode(GetByCodeQuery query) {
        return repository.findByVillageAccountCode(query.code())
                .flatMap(villageAccount -> Mono.just(getVillageAccount(villageAccount)));
    }

    @Override
    public Mono<VillageAccountResponse> getByDate(String date) {
        return repository.findByDate(date)
                .flatMap(villageAccount -> Mono.just(getVillageAccount(villageAccount)));
    }

    private VillageAccountResponse getVillageAccount(VillageAccount account) {
        return new VillageAccountResponse(
                account.getVillageAccountId(),
                account.getVillageAccountCode(),
                account.getAccountNo(),
                account.getVillageCode(),
                account.getVillageName(),
                account.getVillageAddress(),
                account.getDate(),
                account.getStatus(),
                account.getCreatedAt(),
                account.getCreatedDate());
    }
}
