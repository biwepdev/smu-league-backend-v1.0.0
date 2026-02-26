package com.smu.Role.core.payload.impl;

import com.smu.Role.cmd.api.command.RoleCreatedCommand;
import com.smu.Role.core.common.RoleCode;
import com.smu.Role.core.payload.RolePayload;
import com.smu.Role.query.api.repository.RoleRepositories;
import com.smu.Role.query.api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RolePayloadImpl implements RolePayload {
    private final RoleRepository roleRepository;
private final RoleRepositories repositories;

    @Override
    public Mono<Boolean> verify(RoleCreatedCommand command) {
        return roleRepository.existsRoleByDescription(command.description());
    }

    @Override
    public Mono<String> createRoleCode() {
        return roleRepository.count()
                .flatMap(aLong -> {
                    if (aLong == 0) {
                        return Mono.just("ROL000001");
                    }
                    else {
                        Mono<String> code = repositories.getLastElement()
                                .flatMap(role -> Mono.just(role.getRoleCode()));
                        return RoleCode.roleCode(code);
                    }
                });
    }
}
