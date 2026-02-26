package com.smu.user.query.api.handler;

import com.smu.user.core.query.GetByCodeQuery;
import com.smu.user.query.api.response.UserResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserQueryHandler {
    Flux<UserResponse> findUsers();
    Mono<UserResponse> findByCode(GetByCodeQuery query);
    Flux<UserResponse> findAllUsersByStaffCode(GetByCodeQuery query);
    Flux<UserResponse> findAllUsersByRoleCode(GetByCodeQuery query);
    Flux<UserResponse> findAllUsersByMemberCode(GetByCodeQuery query);
}
