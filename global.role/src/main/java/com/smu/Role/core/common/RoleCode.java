package com.smu.Role.core.common;

import reactor.core.publisher.Mono;

public class RoleCode {
    private RoleCode() {}
    public static Mono<String> roleCode(Mono<String> code) {
        return code.map(codeString -> {
            int number = Integer.parseInt(codeString.substring(3));
            return "ROL" + String.format("%06d", number + 1);
        });
    }
}
