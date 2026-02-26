package com.smu.Role.core.payload;

import com.smu.Role.cmd.api.command.RoleCreatedCommand;
import reactor.core.publisher.Mono;

public interface RolePayload {
    Mono<Boolean> verify(RoleCreatedCommand command);
    Mono<String> createRoleCode();
}
