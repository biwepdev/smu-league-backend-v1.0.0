package com.smu.committee.committee.query.api.handler;

import com.smu.committee.committee.core.query.GetByCodeQuery;
import com.smu.committee.committee.query.api.response.CommitteeResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommitteeQueryHandler {
    Flux<CommitteeResponse> findAllCommittees();
    Mono<CommitteeResponse> findByCode(GetByCodeQuery query);
    Flux<CommitteeResponse> findAllEnabledCommittees();
    Flux<CommitteeResponse> findAllDisabledCommittees();
    Flux<CommitteeResponse> findAllCommitteesByVillageCode(GetByCodeQuery query);
    Flux<CommitteeResponse> findByMemberCode(GetByCodeQuery query);
    Flux<CommitteeResponse> findByPostCode(GetByCodeQuery query);
}
