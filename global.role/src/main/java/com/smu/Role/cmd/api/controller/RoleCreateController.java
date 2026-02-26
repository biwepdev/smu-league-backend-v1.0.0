package com.smu.Role.cmd.api.controller;

import com.smu.Role.cmd.api.command.RoleCreatedCommand;
import com.smu.Role.core.dto.MessageResponse;
import com.smu.Role.core.payload.RolePayload;
import com.smu.Role.core.utils.RoleUtilsConstants;
import com.smu.Role.query.api.handler.RoleEventHandler;
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
@RequestMapping(path = "api/v1/role/create-role")
@Tag(name = "role")
public class RoleCreateController {
    private final RoleEventHandler roleEventHandler;
    private final RolePayload rolePayload;

    @Operation(summary = "create role")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody RoleCreatedCommand command) {
                        return rolePayload.verify(command)
                                .flatMap(exists -> {
                                    if (exists) {
                                        return Mono.just(ResponseEntity
                                                .status(HttpStatus.CONFLICT)
                                                .body(new MessageResponse(false, "description existe déjà.")));
                                    } else {
                                        return roleEventHandler.create(command)
                                                .thenReturn(new ResponseEntity<>(new MessageResponse(true, RoleUtilsConstants.CREATE), HttpStatus.CREATED));
                                    }
                                });

    }}
