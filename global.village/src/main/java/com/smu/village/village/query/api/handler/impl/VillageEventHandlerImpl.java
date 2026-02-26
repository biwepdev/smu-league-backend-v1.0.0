package com.smu.village.village.query.api.handler.impl;

import com.smu.village.village.cmd.api.command.VillageCreatedCommand;
import com.smu.village.village.cmd.api.command.VillageEnableCommand;
import com.smu.village.village.cmd.api.command.VillageUpdatedCommand;
import com.smu.village.core.common.LogCreated;
import com.smu.village.core.common.Status;
import com.smu.village.village.core.model.Village;
import com.smu.village.village.core.payload.VillagePayload;
import com.smu.village.village.query.api.handler.VillageEventHandler;
import com.smu.village.village.query.api.repository.VillageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VillageEventHandlerImpl implements VillageEventHandler {
    private final VillageRepository villageRepository;
    private final VillagePayload villagePayload;

    @Override
    public Mono<Village> create(VillageCreatedCommand command) {
        return villagePayload.createVillageCode()
                .flatMap(code -> {
            Village village = Village.builder()
                    .villageId(UUID.randomUUID().toString())
                    .villageName(command.villageName())
                    .country(command.country())
                    .province(command.province())
                    .commune(command.commune())
                    .zone(command.zone())
                    .streetNumber(command.streetNumber())
                    .streetName(command.streetName())
                    .email(command.email())
                    .mobileNo(command.mobileNo())
                    .sectorActivities(command.sectorActivities())
                    .status("ACTIVE")
                    .createdAt(LogCreated.AT())
                    .createdMonth(LogCreated.MONTH())
                    .createdYear(LogCreated.YEAR())
                    .createdDate(LogCreated.DATE())
                    .villageCode(code)
                    .build();
            return villageRepository.save(village);
                });
    }

    @Override
    public Mono<Village> update(VillageUpdatedCommand command) {
        return villageRepository.findById(command.villageId())
                .flatMap(village -> {
            village.setVillageName(command.villageName());
            village.setCountry(command.country());
            village.setProvince(command.province());
            village.setCommune(command.commune());
            village.setZone(command.zone());
            village.setStreetNumber(command.streetNumber());
            village.setStreetName(command.streetName());
            village.setEmail(command.email());
            village.setMobileNo(command.mobileNo());
            village.setSectorActivities(command.sectorActivities());

            return villageRepository.save(village);
                });
    }

    @Override
    public Mono<Village> enable(VillageEnableCommand command) {
        return villageRepository.findByVillageCode (command.villageCode ())
                .flatMap(village -> {
            village.setStatus (Status.enable ());
            return villageRepository.save(village);
        });
    }

    @Override
    public Mono<Village> disable(VillageEnableCommand command) {
        return villageRepository.findByVillageCode (command.villageCode ())
                .flatMap(village -> {
                    village.setStatus (Status.disable ());
                    return villageRepository.save(village);
                });
    }
}
