package com.smu.user.core.payload.impl;

import com.smu.user.core.common.UserCode;
import com.smu.user.core.payload.UserPayload;
import com.smu.user.query.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserPayloadImpl implements UserPayload {
    private final UserRepository userRepository;

    @Override
    public Mono<String> createUserCode() {
        return userRepository.count().flatMap(count -> {
            if (count == 0) {
                return Mono.just("USR000001");
            }
            else {
                return userRepository.findByCodeDesc()
                        .take(1)
                        .single()
                        .flatMap(last ->
                                UserCode.userCode(Mono.just(last.getUserCode())));
            }
        });
    }
}
