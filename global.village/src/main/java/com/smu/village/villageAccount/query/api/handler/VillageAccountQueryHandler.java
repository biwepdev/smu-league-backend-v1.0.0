package com.smu.village.villageAccount.query.api.handler;

import com.smu.village.core.query.GetByCodeQuery;
import com.smu.village.villageAccount.query.api.response.VillageAccountResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VillageAccountQueryHandler {
    Flux<VillageAccountResponse> getVillageAccounts();
    Mono<VillageAccountResponse> getByCode(GetByCodeQuery query);
    Mono<VillageAccountResponse> getByDate(String date);
}
