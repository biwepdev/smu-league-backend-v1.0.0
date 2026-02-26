package com.smu.village.village.core.payload.impl;

import com.smu.village.village.cmd.api.command.VillageCreatedCommand;
import com.smu.village.village.cmd.api.command.VillageUpdatedCommand;
import com.smu.village.core.common.VillageCode;
import com.smu.village.village.core.payload.VillagePayload;
import com.smu.village.village.query.api.repository.VillageRepositories;
import com.smu.village.village.query.api.repository.VillageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class VillagePayloadImpl implements VillagePayload {
    private final VillageRepository villageRepository;
    private final VillageRepositories repositories;

    @Override
    public Mono<Void> createException(VillageCreatedCommand command) {
        return villageRepository.existsByVillageName(command.villageName()).flatMap(exists -> {
            if (exists) {
                return Mono.error(new IllegalArgumentException("A village already exists with the name: " + command.villageName()));
            } else {
                return Mono.empty();
            }
        });
    }

    @Override
    public Mono<Void> updateException(VillageUpdatedCommand command) {
        return villageRepository.findByVillageName(command.villageName()).flatMap(existing -> {
            if (!existing.getVillageCode().equals(command.code())) {
                return Mono.error(new IllegalArgumentException("A village already exists with the name: " + command.villageName()));
            }
            return Mono.empty();
        }).switchIfEmpty(Mono.empty()).then();
    }

    @Override
    public Mono<String> createVillageCode() {
        return villageRepository.count()
                .flatMap(aLong -> {
                    if (aLong == 0) {
                        return Mono.just("VIL000001");
                    }
                    else {
                        Mono<String> code = repositories.getLastElement()
                                .flatMap(village -> Mono.just(village.getVillageCode()));
                        return VillageCode.villageCode(code);
                    }
                });
    }
}
