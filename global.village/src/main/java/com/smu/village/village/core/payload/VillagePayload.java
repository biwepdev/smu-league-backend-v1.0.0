package com.smu.village.village.core.payload;

import com.smu.village.village.cmd.api.command.VillageCreatedCommand;
import com.smu.village.village.cmd.api.command.VillageUpdatedCommand;
import reactor.core.publisher.Mono;

public interface VillagePayload {
    Mono<Void> createException(VillageCreatedCommand command);
    Mono<Void> updateException(VillageUpdatedCommand command);
    Mono<String> createVillageCode();
}
