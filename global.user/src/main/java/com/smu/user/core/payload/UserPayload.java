package com.smu.user.core.payload;

import reactor.core.publisher.Mono;

public interface UserPayload {
    Mono<String> createUserCode();
}
