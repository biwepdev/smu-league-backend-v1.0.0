package com.smu.Role.query.api.handler;

import com.smu.Role.core.query.GetByCodeQuery;
import com.smu.Role.query.api.response.RoleResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleQueryHandler {
    Flux<RoleResponse> findRoles();
    Mono<RoleResponse> findByCode(GetByCodeQuery query);
}
