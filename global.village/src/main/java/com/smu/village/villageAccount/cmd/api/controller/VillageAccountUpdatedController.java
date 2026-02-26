package com.smu.village.villageAccount.cmd.api.controller;

import com.smu.village.core.dto.MessageResponse;
import com.smu.village.core.utils.VillageUtilsConstants;
import com.smu.village.villageAccount.cmd.api.command.VillageAccountCreatedCommand;
import com.smu.village.villageAccount.cmd.api.command.VillageAccountUpdatedCommand;
import com.smu.village.villageAccount.query.api.handler.VillageAccountEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/smu/village/account/update-village-account")
@Tag(name = "villageAccount", description = "Data REST API for villageAccount resource")
public class VillageAccountUpdatedController {

    private final VillageAccountEventHandler eventHandler;

    @Operation(summary = "Update a village account")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update(@Valid @RequestBody VillageAccountUpdatedCommand command) {
        return eventHandler.update(command)
                .thenReturn(new ResponseEntity<>(new MessageResponse(true, VillageUtilsConstants.CREATE), HttpStatus.CREATED))
                .onErrorResume(error ->
                        Mono.just(new ResponseEntity<>(new MessageResponse(false, "Error to update a villageAccount"), HttpStatus.BAD_REQUEST)));
    }
}
