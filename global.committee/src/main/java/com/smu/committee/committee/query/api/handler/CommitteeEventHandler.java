package com.smu.committee.committee.query.api.handler;

import com.smu.committee.committee.cmd.api.command.*;
import com.smu.committee.committee.core.model.Committee;
import reactor.core.publisher.Mono;

public interface CommitteeEventHandler {
    Mono<Committee> create(CommitteeCreatedCommand command);
    Mono<Committee> update(CommitteeUpdatedCommand command);
    Mono<Committee> enable(CommitteeEnableCommand command);
    Mono<Committee> disable(CommitteeEnableCommand command);
    Mono<String> enableAllCommittees(EnableAllCommitteesCommand command);
    Mono<String> disableAllCommittees(DisableAllCommitteesCommand command);

}
