package com.smu.village.villageAccount.cmd.api.controller;


import com.smu.village.core.dto.MessageResponse;
import com.smu.village.core.utils.VillageUtilsConstants;
import com.smu.village.village.cmd.api.command.VillageEnableCommand;
import com.smu.village.village.query.api.handler.VillageEventHandler;
import com.smu.village.villageAccount.cmd.api.command.VillageAccountEnableCommand;
import com.smu.village.villageAccount.query.api.handler.VillageAccountEventHandler;
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
@RequestMapping(path = "api/v1/smu/village/account/enable-village-account")
@Tag(name = "villageAccount",description = "Data REST API for village account resource")
public class VillageAccountEnableController {
    private final VillageAccountEventHandler eventHandler;

    @Operation(summary = "Enable a village account by code")
    @PostMapping("/a-village-account-by-code")
    public Mono<ResponseEntity<MessageResponse>> enable(@Valid @RequestBody VillageAccountEnableCommand command) {
        try {
            return eventHandler.enable(command)
                    .flatMap (villageAccount -> {
                        if ( villageAccount != null ) {
                            return Mono.just ( ResponseEntity.ok ( )
                                    .body ( new MessageResponse(true , VillageUtilsConstants.ENABLE)));
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
