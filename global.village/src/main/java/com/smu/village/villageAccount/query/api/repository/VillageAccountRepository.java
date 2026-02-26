package com.smu.village.villageAccount.query.api.repository;

import com.smu.village.villageAccount.core.model.VillageAccount;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface VillageAccountRepository extends ReactiveMongoRepository<VillageAccount, String> {
    Mono<VillageAccount> findByVillageAccountCode(@Param("villageAccountCode") String villageAccountCode);
    Mono<VillageAccount> findByVillageCode(@Param("villageCode") String villageCode);
    Mono<VillageAccount> findByDate(@Param("date") String date);
    @Query(value ="{}",sort = "{'village_account_code':-1}")
    Flux<VillageAccount> findByCodeDesc();
}
