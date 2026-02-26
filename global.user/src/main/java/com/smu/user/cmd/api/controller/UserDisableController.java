package com.smu.user.cmd.api.controller;

import com.smu.user.cmd.api.command.UserEnableCommand;
import com.smu.user.core.dto.MessageResponse;
import com.smu.user.core.utils.UserUtilsConstants;
import com.smu.user.query.api.handler.UserEventHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/smu/user/disable-user")
@Tag(name = "user",description = "Data REST API for user resource")
public class UserDisableController {
    private final UserEventHandler eventHandler;

    @Operation(summary = "Disable a user by code")
    @PutMapping("/a-user-by-code")
    public Mono<ResponseEntity<MessageResponse>> disable(@Valid @RequestBody UserEnableCommand command) {
        try {
            return eventHandler.disable(command)
                    .flatMap (user -> {
                        if ( user != null ) {
                            return Mono.just ( ResponseEntity.ok ( )
                                    .body ( new MessageResponse(true , UserUtilsConstants.DISABLE)));
                        } else {
                            return Mono.just(ResponseEntity.ok()
                                    .body ( new MessageResponse (false , UserUtilsConstants.OPERATION_FAILED)));
                        }
                    } );
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
