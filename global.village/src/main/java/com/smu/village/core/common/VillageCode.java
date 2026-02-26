package com.smu.village.core.common;

import reactor.core.publisher.Mono;

public class VillageCode {
    private VillageCode() {}
    public static Mono<String> villageCode(Mono<String> code) {
        return code.map(codeString -> {
            int number = Integer.parseInt(codeString.substring(3));
            return "VIL" + String.format("%06d", number + 1);
        });
    }

    public static Mono<String> villageAccountCode(Mono<String> code) {
        return code.map(codeString -> {
            int number = Integer.parseInt(codeString.substring(3));
            return "VAC" + String.format("%06d", number + 1);
        });
    }
}
