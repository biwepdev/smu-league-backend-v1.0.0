package com.smu.user.core.common;

import reactor.core.publisher.Mono;

public class UserCode {
    private UserCode() {}
    public static Mono<String> userCode(Mono<String> code) {
        return code.map(codeString -> {
            int number = Integer.parseInt(codeString.substring(3));
            return "USR" + String.format("%06d", number + 1);
        });
    }
}
