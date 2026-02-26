package com.smu.committee.committee.query.api.handler.impl;

import com.smu.committee.committee.cmd.api.command.*;
import com.smu.committee.committee.core.common.LogCreated;
import com.smu.committee.committee.core.common.Status;
import com.smu.committee.committee.core.model.Committee;
import com.smu.committee.committee.core.payload.CommitteePayload;
import com.smu.committee.committee.query.api.handler.CommitteeEventHandler;
import com.smu.committee.committee.query.api.repository.CommitteeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommitteeEventHandlerImpl implements CommitteeEventHandler {
    private final CommitteeRepository committeeRepository;
    private final CommitteePayload committeePayload;


    @Override
    public Mono<Committee> create(CommitteeCreatedCommand command) {
        return committeePayload.getCode()
                .flatMap(code ->{
                    Committee committee = Committee.builder()
                                            .committeeId(UUID.randomUUID().toString())
                                            .memberCode(command.memberCode())
                                            .memberName(command.memberName())
                                            .memberCitizenId(command.memberCitizenId())
                                            .villageCode(command.villageCode())
                                            .villageName(command.villageName())
                                            .postCode(command.postCode())
                                            .postName(command.postName())
                                            .enabled(true)
                                            .state(Status.enable())
                                            .createAt(LogCreated.At())
                                            .createdMonth(LogCreated.Month())
                                            .createdYear(LogCreated.Year())
                                            .createdDate(LogCreated.Date())
                                            .committeeCode(code)
                                            .build();
                                    return committeeRepository.save(committee);
                                });
                }
    @Override
    public Mono<Committee> update(CommitteeUpdatedCommand command) {
        return committeeRepository.findById(command.committeeId())
                .flatMap(committee ->
                {
                    committee.setMemberCode(command.memberCode());
                    committee.setMemberName(command.memberName());
                    committee.setMemberCitizenId(command.memberCitizenId());
                    committee.setVillageCode(command.villageCode());
                    committee.setVillageName(command.villageName());
                    committee.setPostCode(command.postCode());
                    committee.setPostName(command.postName());

                    return committeeRepository.save(committee);
                });
    }

    @Override
    public Mono<Committee> enable(CommitteeEnableCommand command) {
        return committeeRepository.findByCommitteeCode (command.committeeCode ()).flatMap(committee -> {
            committee.setState (Status.enable ());
            committee.setEnabled(true);
            return committeeRepository.save(committee);
        });
    }

    @Override
    public Mono<Committee> disable(CommitteeEnableCommand command)
    {
        return committeeRepository.findByCommitteeCode (command.committeeCode ()).flatMap(committee -> {
            committee.setState (Status.disable ());
            committee.setEnabled(false);
            return committeeRepository.save(committee);
        });
    }

    @Override
    public Mono<String> enableAllCommittees(EnableAllCommitteesCommand command) {
        return committeeRepository.findAll()
                .flatMap(committee -> {
                    committee.setEnabled(true);
                    committee.setState("01");
                    return committeeRepository.save(committee);
    })

                .then(Mono.just("{\"success\": true, \"message\": \"All have been successfully enabled\"}"));
    }

    @Override
    public Mono<String> disableAllCommittees(DisableAllCommitteesCommand command) {
        return committeeRepository.findAll()
                .flatMap(committee -> {
                    committee.setEnabled(false);
                    committee.setState("00");
                    return committeeRepository.save(committee);
                })
                .then(Mono.just("{\"success\": true, \"message\": \"All have been successfully disabled\"}"));
    }

}
