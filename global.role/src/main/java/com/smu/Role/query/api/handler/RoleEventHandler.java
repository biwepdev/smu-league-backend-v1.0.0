package com.smu.Role.query.api.handler;

import com.smu.Role.cmd.api.command.RoleCreatedCommand;
import com.smu.Role.cmd.api.command.RoleUpdatedCommand;
import com.smu.Role.core.model.Role;
import reactor.core.publisher.Mono;

public interface RoleEventHandler {
    Mono<Role> create(RoleCreatedCommand command);
    Mono<Role> update(RoleUpdatedCommand command);
}
