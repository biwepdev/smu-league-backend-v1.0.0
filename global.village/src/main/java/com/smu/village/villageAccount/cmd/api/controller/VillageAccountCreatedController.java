package com.smu.village.villageAccount.cmd.api.controller;

import com.smu.village.core.dto.MessageResponse;
import com.smu.village.core.utils.VillageUtilsConstants;
import com.smu.village.villageAccount.cmd.api.command.VillageAccountCreatedCommand;
import com.smu.village.villageAccount.query.api.handler.VillageAccountEventHandler;
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
@RequestMapping(path = "api/v1/smu/village/account/create-village-account")
@Tag(name = "villageAccount", description = "Data REST API for villageAccount resource")
public class VillageAccountCreatedController {

    private final VillageAccountEventHandler eventHandler;

    @Operation(summary = "Create a village account")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody VillageAccountCreatedCommand command) {
        return eventHandler.create(command)
                .thenReturn(new ResponseEntity<>(new MessageResponse(true, VillageUtilsConstants.CREATE), HttpStatus.CREATED))
                .onErrorResume(error ->
                        Mono.just(new ResponseEntity<>(new MessageResponse(false, "Error to create a villageAccount"), HttpStatus.BAD_REQUEST)));
    }
}
