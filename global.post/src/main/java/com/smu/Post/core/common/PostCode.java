package com.smu.Post.core.common;

import reactor.core.publisher.Mono;

public class PostCode {
    private PostCode() {}
    public static Mono<String> postCode(Mono<String> code) {
        return code.map(codeString -> {
            int number = Integer.parseInt(codeString.substring(3));
            return "POS" + String.format("%06d", number + 1);
        });
    }
}
