package com.smu.user.cmd.api.controller;

import com.smu.user.cmd.api.command.UserChangeRoleCommand;
import com.smu.user.core.dto.MessageResponse;
import com.smu.user.query.api.handler.UserEventHandler;
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
@RequestMapping(path = "api/v1/user/change-user-role")
@Tag(name = "user")
public class UserChangeRoleController {
    private final UserEventHandler userEventHandler;

    @PostMapping("/change-role")
    @Operation(summary = "Change user role")
    public Mono<ResponseEntity<MessageResponse>> changeRole(
            @Valid @RequestBody UserChangeRoleCommand command
    ) {
        return userEventHandler.changeRole(command)
                .map(u -> ResponseEntity.ok(new MessageResponse(true, "Mise à jour du rôle utilisateur effectuée")))
                .onErrorResume(ex ->
                        Mono.just(ResponseEntity.badRequest()
                                .body(new MessageResponse(false, ex.getMessage())))
                );
    }
}
