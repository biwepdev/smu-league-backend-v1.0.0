package com.smu.committee.committee.cmd.api.controller;

import com.smu.committee.committee.core.dto.MessageResponse;
import com.smu.committee.committee.core.utils.MapUtils;
import com.smu.committee.committee.cmd.api.command.CommitteeUpdatedCommand;
import com.smu.committee.committee.query.api.handler.CommitteeEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/smu/software/committee/update-committee")
@Tag(name = "committee", description = "Data REST API for committee resource")
public class CommitteeUpdateController {
    private final CommitteeEventHandler committeeEventHandler;

    @Operation(summary = "update a committee")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update(@Valid @RequestBody CommitteeUpdatedCommand command)
    {
        return committeeEventHandler.update(command)
                .thenReturn(new ResponseEntity<>(new MessageResponse(true, MapUtils.UPDATE), HttpStatus.CREATED))
                .onErrorResume(error ->
                        Mono.just(new ResponseEntity<>(new MessageResponse(false, "Error to update a committee"), HttpStatus.BAD_REQUEST)));
    }

    }
