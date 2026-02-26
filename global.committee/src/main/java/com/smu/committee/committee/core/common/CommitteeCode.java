package com.smu.committee.committee.core.common;

import reactor.core.publisher.Mono;

public class CommitteeCode {
    public static Mono<String> generate(Mono<String> code) {
        return code.map(codeString -> codeString.substring(3))
                .map(Integer::parseInt)
                .map(number -> number + 1)
                .map(number -> String.format("COM%06d", number));
    }
}
