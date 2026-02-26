package com.smu.Post.cmd.api.controller;

import com.smu.Post.cmd.api.command.PostUpdatedCommand;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post/update-post")
@Tag(name = "post")
public class PostUpdateCommand {
    private final PostEventHandler postEventHandler;

    @Operation(summary = "update post")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update(@Valid @RequestBody PostUpdatedCommand command)
    {
        return postEventHandler.update(command)
                .thenReturn(new ResponseEntity<>(new MessageResponse(true, PostUtilsConstants.UPDATE), HttpStatus.CREATED))
                .onErrorResume(error ->
                        Mono.just(new ResponseEntity<>(new MessageResponse(false, "Error to update a post"), HttpStatus.BAD_REQUEST)));
    }

    }
