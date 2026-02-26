package com.smu.Post.cmd.api.controller;

import com.smu.Post.cmd.api.command.PostCreatedCommand;
import com.smu.Post.core.dto.MessageResponse;
import com.smu.Post.core.payload.PostPayload;
import com.smu.Post.core.utils.PostUtilsConstants;
import com.smu.Post.query.api.handler.PostEventHandler;
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
@RequestMapping(path = "api/v1/post/create-post")
@Tag(name = "post")
public class PostCreateController {
    private final PostEventHandler postEventHandler;
    private final PostPayload postPayload;

    @Operation(summary = "create post")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody PostCreatedCommand command) {
                        return postPayload.verify(command)
                                .flatMap(exists -> {
                                    if (exists) {
                                        return Mono.just(ResponseEntity
                                                .status(HttpStatus.CONFLICT)
                                                .body(new MessageResponse(false, "description existe déjà.")));
                                    } else {
                                        return postEventHandler.create(command)
                                                .thenReturn(new ResponseEntity<>(new MessageResponse(true, PostUtilsConstants.CREATE), HttpStatus.CREATED));
                                    }
                                });

    }}
