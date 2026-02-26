package com.smu.Post.core.payload.impl;

import com.smu.Post.cmd.api.command.PostCreatedCommand;
import com.smu.Post.core.common.PostCode;
import com.smu.Post.core.payload.PostPayload;
import com.smu.Post.query.api.repository.PostRepositories;
import com.smu.Post.query.api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostPayloadImpl implements PostPayload {
    private final PostRepository postRepository;
private final PostRepositories repositories;

    @Override
    public Mono<Boolean> verify(PostCreatedCommand command) {
        return postRepository.existsPostByDescription(command.description());
    }

    @Override
    public Mono<String> createPostCode() {
        return postRepository.count()
                .flatMap(aLong -> {
                    if (aLong == 0) {
                        return Mono.just("POS000001");
                    }
                    else {
                        Mono<String> code = repositories.getLastElement()
                                .flatMap(post -> Mono.just(post.getPostCode()));
                        return PostCode.postCode(code);
                    }
                });
    }
}
