package com.smu.village.village.cmd.api.controller;

import com.smu.village.village.cmd.api.command.VillageEnableCommand;
import com.smu.village.core.dto.MessageResponse;
import com.smu.village.core.utils.VillageUtilsConstants;
import com.smu.village.village.query.api.handler.VillageEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/smu/village/disable-village")
@Tag(name = "village",description = "Data REST API for village resource")
public class VillageDisableController {
    private final VillageEventHandler eventHandler;

    @Operation(summary = "Disable a village by code")
    @PostMapping("/a-village-by-code")
    public Mono<ResponseEntity<MessageResponse>> disable(@Valid @RequestBody VillageEnableCommand command) {
        try {
            return eventHandler.disable(command)
                    .flatMap (village -> {
                        if ( village != null ) {
                            return Mono.just ( ResponseEntity.ok ( )
                                    .body ( new MessageResponse(true , VillageUtilsConstants.DISABLE)));
                        } else {
                            return Mono.just(ResponseEntity.ok()
                                    .body ( new MessageResponse (false , VillageUtilsConstants.OPERATION_FAILED)));
                        }
                    } );
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
