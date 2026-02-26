package com.smu.village.village.query.api.repository;

import com.smu.village.village.core.model.Village;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface VillageRepository extends ReactiveMongoRepository<Village,String> {
    Mono<Boolean> existsByVillageName(@Param("villageName") String villageName);
    Mono<Village> findByVillageName(@Param("villageName") String villageName);
    Mono<Village> findByVillageCode(@Param("villageCode") String villageCode);
    @Query(value ="{}",sort = "{'villageCode':-1}")
    Flux<Village> findByCodeDesc();
}
