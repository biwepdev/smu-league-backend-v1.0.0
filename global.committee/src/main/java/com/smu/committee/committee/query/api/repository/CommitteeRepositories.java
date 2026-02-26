package com.smu.committee.committee.query.api.repository;

import com.smu.committee.committee.core.model.Committee;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CommitteeRepositories {
    Mono<Committee> getLastElement();
}
