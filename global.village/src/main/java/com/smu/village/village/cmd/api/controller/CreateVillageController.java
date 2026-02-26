package com.smu.village.village.cmd.api.controller;

import com.smu.village.village.cmd.api.command.VillageCreatedCommand;
import com.smu.village.core.dto.MessageResponse;
import com.smu.village.village.core.payload.VillagePayload;
import com.smu.village.core.utils.VillageUtilsConstants;
import com.smu.village.village.query.api.handler.VillageEventHandler;
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
@RequestMapping(path = "api/v1/smu/village/create-village")
@Tag(name = "village", description = "Data REST API for village resource")
public class CreateVillageController {
    private final VillagePayload villagePayload;
    private final VillageEventHandler villageEventHandler;
    
    @Operation(summary = "Create village")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> create(@Valid @RequestBody VillageCreatedCommand command) {
        return villagePayload.createException(command).then(villageEventHandler.create(command))
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(new MessageResponse(true, VillageUtilsConstants.CREATE)))
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().body(new MessageResponse(false, "Erreur de l'opération !"))))
                .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(false, "Erreur serveur : " + ex.getMessage()))));
    }
}
