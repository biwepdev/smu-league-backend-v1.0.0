package com.smu.village.villageAccount.query.api.handler.impl;

import com.smu.village.core.common.LogCreated;
import com.smu.village.core.common.Status;
import com.smu.village.villageAccount.cmd.api.command.VillageAccountCreatedCommand;
import com.smu.village.villageAccount.cmd.api.command.VillageAccountEnableCommand;
import com.smu.village.villageAccount.cmd.api.command.VillageAccountUpdatedCommand;
import com.smu.village.villageAccount.core.model.VillageAccount;
import com.smu.village.villageAccount.core.payload.VillageAccountPayload;
import com.smu.village.villageAccount.query.api.handler.VillageAccountEventHandler;
import com.smu.village.villageAccount.query.api.repository.VillageAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VillageAccountEventHandlerImpl implements VillageAccountEventHandler {
    private final VillageAccountRepository repository;
    private final VillageAccountPayload payload;

    @Override
    public Mono<VillageAccount> create(VillageAccountCreatedCommand command) {
        return payload.createCode()
                .flatMap(code -> {
                    VillageAccount villageAccount = VillageAccount.builder()
                            .villageAccountId(UUID.randomUUID().toString())
                            .accountNo(command.accountNo())
                            .villageCode(command.villageCode())
                            .villageName(command.villageName())
                            .villageAddress(command.villageAddress())
                            .date(command.date())
                            .status("ACTIVE")
                            .createdAt(LogCreated.AT())
                            .createdMonth(LogCreated.MONTH())
                            .createdYear(LogCreated.YEAR())
                            .createdDate(LogCreated.DATE())
                            .villageAccountCode(code)
                            .build();
                    return repository.save(villageAccount);
                });
    }

    @Override
    public Mono<VillageAccount> update(VillageAccountUpdatedCommand command) {
        return repository.findById(command.villageAccountId())
                .flatMap(villageAccount ->
                {
                    villageAccount.setAccountNo(command.accountNo());
                    villageAccount.setVillageCode(command.villageCode());
                    villageAccount.setVillageName(command.villageName());
                    villageAccount.setVillageAddress(command.villageAddress());
                    villageAccount.setDate(command.date());
                    return repository.save(villageAccount);
                });
    }

    @Override
    public Mono<VillageAccount> enable(VillageAccountEnableCommand command) {
        return repository.findByVillageAccountCode (command.villageAccountCode ())
                .flatMap(villageAccount -> {
                    villageAccount.setStatus (Status.enable ());
                    return repository.save(villageAccount);
                });
    }

    @Override
    public Mono<VillageAccount> disable(VillageAccountEnableCommand command) {
        return repository.findByVillageAccountCode (command.villageAccountCode ())
                .flatMap(villageAccount -> {
                    villageAccount.setStatus (Status.disable ());
                    return repository.save(villageAccount);
                });
    }
}
