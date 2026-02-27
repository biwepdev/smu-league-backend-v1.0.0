package com.smu.user.query.api.handler.impl;

import com.smu.user.core.model.User;
import com.smu.user.core.query.GetByCodeQuery;
import com.smu.user.query.api.handler.UserQueryHandler;
import com.smu.user.query.api.repository.UserRepository;
import com.smu.user.query.api.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserQueryHandlerImpl implements UserQueryHandler {
    private final UserRepository userRepository;
    @Override
    public Flux<UserResponse> findUsers() {
        return userRepository.findAll()
                .flatMap(user -> Mono.just(getUser(user)));
    }

    @Override
    public Mono<UserResponse> findByCode(GetByCodeQuery query) {
        return userRepository.findByUserCode(query.code())
                .map(this::getUser);
    }

    @Override
    public Flux<UserResponse> findAllUsersByStaffCode(GetByCodeQuery query) {
        return userRepository.findByStaffCode(query.code())
                .map(this::getUser);
    }

    @Override
    public Flux<UserResponse> findAllUsersByRoleCode(GetByCodeQuery query) {
        return userRepository.findByRoleCode(query.code())
                .map(this::getUser);
    }

    @Override
    public Flux<UserResponse> findAllUsersByMemberCode(GetByCodeQuery query) {
        return userRepository.findByMemberCode(query.code())
                .map(this::getUser);
    }

    private UserResponse getUser(User user) {
        return new UserResponse(
                user.getUserId(),
                user.getUserCode(),
                user.getFullName(),
                user.getUserName(),
                user.getTelephone(),
                user.getEmail(),
                user.getMemberCode(),
                user.getStaffCode(),
                user.getRoleCode(),
                user.getRoleName(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getCreatedDate());
    }
}
