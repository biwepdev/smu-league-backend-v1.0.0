package com.smu.Post.core.payload;

import com.smu.Post.cmd.api.command.PostCreatedCommand;
import reactor.core.publisher.Mono;

public interface PostPayload {
    Mono<Boolean> verify(PostCreatedCommand command);
    Mono<String> createPostCode();
}
