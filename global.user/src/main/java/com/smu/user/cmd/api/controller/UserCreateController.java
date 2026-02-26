package com.smu.user.cmd.api.controller;

import com.smu.user.cmd.api.command.UserCreatedCommand;
import com.smu.user.core.dto.MessageResponse;
import com.smu.user.core.payload.UserPayload;
import com.smu.user.core.utils.UserUtilsConstants;
import com.smu.user.query.api.handler.UserEventHandler;
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
@RequestMapping(path = "api/v1/user/create-user")
@Tag(name = "user")
public class UserCreateController {
    private final UserEventHandler userEventHandler;

    @Operation(summary = "create user")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody UserCreatedCommand command) {
                                        return userEventHandler.create(command)
                                                .thenReturn(new ResponseEntity<>(new MessageResponse(true, UserUtilsConstants.CREATE), HttpStatus.CREATED));

    }}
