package com.smu.village.village.cmd.api.controller;

import com.smu.village.village.cmd.api.command.VillageUpdatedCommand;
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

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/smu/village/update-village")
@Tag(name = "village", description = "Data REST API for village resource")
public class UpdateVillageController {
    private final VillageEventHandler villageEventHandler;
    private final VillagePayload villagePayload;

    @Operation(summary = "Update a village")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<MessageResponse>> update(@Valid @RequestBody VillageUpdatedCommand command) {
        return villagePayload.updateException(command).then(villageEventHandler.update(command)
                .map(saved -> ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(true, VillageUtilsConstants.UPDATE)))
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().body(new MessageResponse(false, VillageUtilsConstants.OPERATION_FAILED))))
                .onErrorResume(ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse(false, "Error server : " + ex.getMessage())))));
    }
}
