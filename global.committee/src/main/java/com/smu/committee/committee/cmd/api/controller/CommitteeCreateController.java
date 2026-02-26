package com.smu.committee.committee.cmd.api.controller;

import com.smu.committee.committee.cmd.api.command.CommitteeCreatedCommand;
import com.smu.committee.committee.core.dto.MessageResponse;
import com.smu.committee.committee.core.utils.MapUtils;
import com.smu.committee.committee.query.api.handler.CommitteeEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/smu/software/committee/create-committee")
@Tag(name = "committee", description = "Data REST API for committee resource")
public class CommitteeCreateController {
    private final CommitteeEventHandler committeeEventHandler;

    @Operation(summary = "Create a committee")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody CommitteeCreatedCommand command) {

        return committeeEventHandler.create(command)
                .thenReturn(new ResponseEntity<>(new MessageResponse(true, MapUtils.CREATE), HttpStatus.CREATED))
                .onErrorResume(error ->
                        Mono.just(new ResponseEntity<>(new MessageResponse(false, "Error to create a committee"), HttpStatus.BAD_REQUEST)));
    }
    }
