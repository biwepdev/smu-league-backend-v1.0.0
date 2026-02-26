package com.smu.village.village.query.api.handler.impl;

import com.smu.village.village.core.model.Village;
import com.smu.village.core.query.GetByCodeQuery;
import com.smu.village.village.query.api.handler.VillageQueryHandler;
import com.smu.village.village.query.api.repository.VillageRepository;
import com.smu.village.village.query.api.response.VillageResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class VillageQueryHandlerImpl implements VillageQueryHandler {
    private final VillageRepository villageRepository;
    
    @Override
    public Flux<VillageResponse> findVillages() {
        return villageRepository.findAll()
                .flatMap(village -> Mono.just(getVillage(village)));
    }
    
    @Override
    public Mono<VillageResponse> findByVillageCode(GetByCodeQuery query) {
        return villageRepository.findByVillageCode(query.code())
                .flatMap(village -> Mono.just(getVillage(village)));
    }
    
    private VillageResponse getVillage(Village village) {
        return new VillageResponse(
                village.getVillageId(),
                village.getVillageCode(),
                village.getVillageName(),
                village.getCountry(),
                village.getProvince(),
                village.getCommune(),
                village.getZone(),
                village.getStreetNumber(),
                village.getStreetName(),
                village.getEmail(),
                village.getMobileNo(),
                village.getSectorActivities(),
                village.getStatus(),
                village.getCreatedAt(),
                village.getCreatedDate());
    }
}
