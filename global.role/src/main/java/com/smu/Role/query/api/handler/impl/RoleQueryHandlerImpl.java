package com.smu.Role.query.api.handler.impl;

import com.smu.Role.core.model.Role;
import com.smu.Role.core.query.GetByCodeQuery;
import com.smu.Role.query.api.handler.RoleQueryHandler;
import com.smu.Role.query.api.repository.RoleRepository;
import com.smu.Role.query.api.response.RoleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RoleQueryHandlerImpl implements RoleQueryHandler {
    private final RoleRepository roleRepository;
    @Override
    public Flux<RoleResponse> findRoles() {
        return roleRepository.findAll()
                .flatMap(role -> Mono.just(getRole(role)));
    }

    @Override
    public Mono<RoleResponse> findByCode(GetByCodeQuery query) {
        return roleRepository.findByRoleCode(query.code())
                .map(this::getRole);
    }

    private RoleResponse getRole(Role role) {
        return new RoleResponse(
                role.getRoleId(),
                role.getRoleCode(),
                role.getName(),
                role.getDescription());
    }
}
