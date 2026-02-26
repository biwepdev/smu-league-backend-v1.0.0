package com.smu.village.villageAccount.query.api.handler;

import com.smu.village.villageAccount.cmd.api.command.VillageAccountCreatedCommand;
import com.smu.village.villageAccount.cmd.api.command.VillageAccountEnableCommand;
import com.smu.village.villageAccount.cmd.api.command.VillageAccountUpdatedCommand;
import com.smu.village.villageAccount.core.model.VillageAccount;
import reactor.core.publisher.Mono;

public interface VillageAccountEventHandler {
    Mono<VillageAccount> create(VillageAccountCreatedCommand command);
    Mono<VillageAccount> update(VillageAccountUpdatedCommand command);
    Mono<VillageAccount> enable(VillageAccountEnableCommand command);
    Mono<VillageAccount> disable(VillageAccountEnableCommand command);
}
