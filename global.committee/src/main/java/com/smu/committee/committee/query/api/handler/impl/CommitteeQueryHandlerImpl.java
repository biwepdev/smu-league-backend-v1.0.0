package com.smu.committee.committee.query.api.handler.impl;

import com.smu.committee.committee.core.model.Committee;
import com.smu.committee.committee.core.query.GetByCodeQuery;
import com.smu.committee.committee.query.api.handler.CommitteeQueryHandler;
import com.smu.committee.committee.query.api.repository.CommitteeRepository;
import com.smu.committee.committee.query.api.response.CommitteeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CommitteeQueryHandlerImpl implements CommitteeQueryHandler {
    private final CommitteeRepository committeeRepository;

    @Override
    public Flux<CommitteeResponse> findAllCommittees() {
        return committeeRepository.findAll()
                .flatMap(committee -> Mono.just(getCommittee(committee)));
    }

    @Override
    public Mono<CommitteeResponse> findByCode(GetByCodeQuery query) {
        return committeeRepository.findByCommitteeCode(query.code())
                .map(this::getCommittee);
    }

    @Override
    public Flux<CommitteeResponse> findAllEnabledCommittees() {
        return committeeRepository.findByEnabled(true)
                .map(this::getCommittee);
    }

    @Override
    public Flux<CommitteeResponse> findAllDisabledCommittees() {
        return committeeRepository.findByEnabled(false)
                .map(this::getCommittee);
    }

    @Override
    public Flux<CommitteeResponse> findAllCommitteesByVillageCode(GetByCodeQuery query) {
        return committeeRepository.findByVillageCode(query.code())
                .map(this::getCommittee);
    }

    @Override
    public Flux<CommitteeResponse> findByMemberCode(GetByCodeQuery query) {
        return committeeRepository.findByMemberCode(query.code())
                .map(this::getCommittee);
    }

    @Override
    public Flux<CommitteeResponse> findByPostCode(GetByCodeQuery query) {
        return committeeRepository.findByPostCode(query.code())
                .map(this::getCommittee);
    }

    private CommitteeResponse getCommittee(Committee committee) {
        return new CommitteeResponse(
                committee.getCommitteeId(),
                committee.getCommitteeCode(),
                committee.getMemberCode(),
                committee.getMemberName(),
                committee.getMemberCitizenId(),
                committee.getVillageCode(),
                committee.getVillageName(),
                committee.getPostCode(),
                committee.getPostName(),
                committee.getState(),
                committee.getCreateAt(),
                committee.getCreatedMonth(),
                committee.getCreatedYear(),
                committee.getCreatedDate());
    }
}
