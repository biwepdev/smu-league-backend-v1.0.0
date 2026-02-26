package com.smu.village.village.query.api.handler;

import com.smu.village.core.query.GetByCodeQuery;
import com.smu.village.village.query.api.response.VillageResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VillageQueryHandler {
    Flux<VillageResponse> findVillages();
    Mono<VillageResponse> findByVillageCode(GetByCodeQuery query);
}
