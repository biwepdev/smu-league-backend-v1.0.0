package com.smu.Role.query.api.handler.impl;

import com.smu.Role.cmd.api.command.RoleCreatedCommand;
import com.smu.Role.cmd.api.command.RoleUpdatedCommand;
import com.smu.Role.core.model.Role;
import com.smu.Role.core.payload.RolePayload;
import com.smu.Role.query.api.handler.RoleEventHandler;
import com.smu.Role.query.api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoleEventHandlerImpl implements RoleEventHandler {
    private final RoleRepository roleRepository;
    private final RolePayload rolePayload;

    @Override
    public Mono<Role> create(RoleCreatedCommand command) {
                        return rolePayload.createRoleCode()
                                .flatMap(code ->
                                {
                                    Role role = Role.builder()
                                            .roleId(UUID.randomUUID().toString())
                                            .name(command.name())
                                            .description(command.description())
                                            .roleCode(code)
                                            .build();
                                    return roleRepository.save(role);
                                });
                }


    @Override
    public Mono<Role> update(RoleUpdatedCommand command) {
        return roleRepository.findById(command.roleId())
                .flatMap(role ->
                {
                    role.setName(command.name());
                    role.setDescription(command.description());
                    return roleRepository.save(role);
                });
    }
}
