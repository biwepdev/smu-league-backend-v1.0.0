package com.smu.committee.committee.query.api.repository;

import com.smu.committee.committee.core.model.Committee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CommitteeRepository extends ReactiveMongoRepository<Committee, String > {
    Mono<Committee> findByCommitteeCode(@Param("committeeCode") String committeeCode);
    Flux<Committee> findByEnabled(@Param("enabled") boolean enabled);
    Flux<Committee> findByVillageCode(@Param("villageCode") String villageCode);
    Flux<Committee> findByMemberCode (@Param("memberCode") String memberCode);
    Flux<Committee> findByPostCode (@Param("postCode") String postCode);
}
