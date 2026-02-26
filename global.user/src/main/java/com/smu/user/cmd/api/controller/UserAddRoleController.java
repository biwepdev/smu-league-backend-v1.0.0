package com.smu.user.cmd.api.controller;

import com.smu.user.cmd.api.command.AddRoleCommand;
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
@RequestMapping(path = "api/v1/user/add-user-role")
@Tag(name = "user")
public class UserAddRoleController {
    private final UserEventHandler userEventHandler;

    @PostMapping("/add-role")
    @Operation(summary = "Add role to user")
    public Mono<ResponseEntity<MessageResponse>> addRole(
            @Valid @RequestBody AddRoleCommand command
    ) {
        return userEventHandler.addRole(command)
                .map(u -> ResponseEntity.ok(new MessageResponse(true, "Ajout du rôle réussi")))
                .onErrorResume(ex ->
                        Mono.just(ResponseEntity.badRequest()
                                .body(new MessageResponse(false, ex.getMessage())))
                );
    }
}
