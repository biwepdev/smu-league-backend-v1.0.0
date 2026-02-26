package com.smu.village.village.query.api.handler;

import com.smu.village.village.cmd.api.command.VillageCreatedCommand;
import com.smu.village.village.cmd.api.command.VillageEnableCommand;
import com.smu.village.village.cmd.api.command.VillageUpdatedCommand;
import com.smu.village.village.core.model.Village;
import reactor.core.publisher.Mono;

public interface VillageEventHandler {
    Mono<Village> create(VillageCreatedCommand command);
    Mono<Village> update(VillageUpdatedCommand command);
    Mono<Village> enable(VillageEnableCommand command);
    Mono<Village> disable(VillageEnableCommand command);
}
