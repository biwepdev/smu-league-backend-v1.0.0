package com.smu.Role.cmd.api.controller;

import com.smu.Role.cmd.api.command.RoleUpdatedCommand;
import com.smu.Role.core.dto.MessageResponse;
import com.smu.Role.core.utils.RoleUtilsConstants;
import com.smu.Role.query.api.handler.RoleEventHandler;
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
@RequestMapping("/api/v1/role/update-role")
@Tag(name = "role")
public class RoleUpdateCommand {
    private final RoleEventHandler roleEventHandler;

    @Operation(summary = "update role")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update(@Valid @RequestBody RoleUpdatedCommand command)
    {
        return roleEventHandler.update(command)
                .thenReturn(new ResponseEntity<>(new MessageResponse(true, RoleUtilsConstants.UPDATE), HttpStatus.CREATED))
                .onErrorResume(error ->
                        Mono.just(new ResponseEntity<>(new MessageResponse(false, "Error to update a role"), HttpStatus.BAD_REQUEST)));
    }

    }
