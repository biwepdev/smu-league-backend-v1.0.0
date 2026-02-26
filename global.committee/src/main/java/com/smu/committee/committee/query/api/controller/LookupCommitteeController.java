package com.smu.committee.committee.query.api.controller;

import com.smu.committee.committee.cmd.api.command.EnableAllCommitteesCommand;
import com.smu.committee.committee.core.common.State;
import com.smu.committee.committee.core.query.GetByCodeQuery;
import com.smu.committee.committee.query.api.dto.AllLookupCommitteeResponse;
import com.smu.committee.committee.query.api.dto.LookupCommitteeResponse;
import com.smu.committee.committee.query.api.dto.LookupStateList;
import com.smu.committee.committee.query.api.response.StateResponse;
import com.smu.committee.committee.cmd.api.command.DisableAllCommitteesCommand;
import com.smu.committee.committee.query.api.handler.CommitteeEventHandler;
import com.smu.committee.committee.query.api.handler.CommitteeQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/smu/software/committee/lookup-committee/")
@Tag(name = "committee", description = "Data REST API for committee resource")
public class LookupCommitteeController {
    private final CommitteeQueryHandler committeeQueryHandler;
    private final CommitteeEventHandler committeeEventHandler;

    @Operation(summary = "Retrieve all committees")
    @GetMapping(path = "get-all-committees")
    public Mono<AllLookupCommitteeResponse> findAll(){
        return committeeQueryHandler.findAllCommittees()
                .collectList()
                .map(list-> new AllLookupCommitteeResponse(true, list));
    }

    @Operation(summary = "Retrieve committee by committeeCode")
    @PutMapping(path = "get-committee-by-committee-code")
    public Mono<LookupCommitteeResponse> getCommitteeByCode(@Valid @RequestBody GetByCodeQuery query)
    {
        return committeeQueryHandler.findByCode(query)
                .map(committee-> new LookupCommitteeResponse(true, committee));
    }

    @Operation(summary = "Retrieve all enabled committees")
    @GetMapping(path = "get-all-enabled-committees", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AllLookupCommitteeResponse> getAllEnabledCommittees() {
        return committeeQueryHandler.findAllEnabledCommittees()
                .collectList()
                .map(list ->new AllLookupCommitteeResponse(true, list));
    }

    @Operation(summary = "Retrieve all disable committees")
    @GetMapping(path = "get-all-disable-committees", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AllLookupCommitteeResponse> getAllDisableCommittees(){
        return committeeQueryHandler.findAllDisabledCommittees()
                .collectList()
                .map(list ->new AllLookupCommitteeResponse(true, list));
    }

    @Operation(summary = "Retrieve committee by villageCode")
    @PutMapping(path = "get-committee-by-village-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LookupCommitteeResponse> findByVillageCode(@Valid @RequestBody GetByCodeQuery query){
        return committeeQueryHandler.findAllCommitteesByVillageCode(query)
                .map(committee->new LookupCommitteeResponse(true, committee));
    }

    @Operation(summary = "Retrieve committee by memberCode")
    @PutMapping(path = "get-committee-by-member-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LookupCommitteeResponse> findByMemberCode(@Valid @RequestBody GetByCodeQuery query){
        return committeeQueryHandler.findByMemberCode(query)
                .map(committee->new LookupCommitteeResponse(true, committee));
    }

    @Operation(summary = "Retrieve committee by postCode")
    @PutMapping(path = "get-committee-by-post-code", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LookupCommitteeResponse> findByPostCode(@Valid @RequestBody GetByCodeQuery query){
        return committeeQueryHandler.findByPostCode(query)
                .map(committee->new LookupCommitteeResponse(true, committee));
    }

                  ////////////State////////
    @Operation(summary = "Retrieve state")
    @GetMapping(path = "get-state-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LookupStateList<StateResponse>> getStateList() {
        List<StateResponse> stateList = Arrays.stream(State.values())
                .map(state -> new StateResponse(
                        state.getCode(),
                        state.getLabel()))
                .collect(Collectors.toList());

        return Mono.just(new LookupStateList<>(true, stateList));
    }

    @Operation(summary = "Enable all committees")
    @PutMapping(path = "enable-all-committees", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> enableAllCommittees() {
        return committeeEventHandler.enableAllCommittees(new EnableAllCommitteesCommand())
                .map(message->ResponseEntity.ok(message));
    }

    @Operation(summary = "Disable all committees")
    @PutMapping(path = "disable-all-committees", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> disableAllCommittees(){
        return committeeEventHandler.disableAllCommittees(new DisableAllCommitteesCommand())
                .map(message-> ResponseEntity.ok(message));
    }
}
