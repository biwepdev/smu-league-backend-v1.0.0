package com.smu.village.villageAccount.core.payload;

import reactor.core.publisher.Mono;

public interface VillageAccountPayload {
    Mono<String> createCode();
}
