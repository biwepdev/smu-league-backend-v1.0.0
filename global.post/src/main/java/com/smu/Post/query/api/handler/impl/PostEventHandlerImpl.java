package com.smu.Post.query.api.handler.impl;

import com.smu.Post.cmd.api.command.PostCreatedCommand;
import com.smu.Post.cmd.api.command.PostUpdatedCommand;
import com.smu.Post.core.model.Post;
import com.smu.Post.core.payload.PostPayload;
import com.smu.Post.query.api.handler.PostEventHandler;
import com.smu.Post.query.api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PostEventHandlerImpl implements PostEventHandler {
    private final PostRepository postRepository;
    private final PostPayload postPayload;

    @Override
    public Mono<Post> create(PostCreatedCommand command) {
                        return postPayload.createPostCode()
                                .flatMap(code ->
                                {
                                    Post post = Post.builder()
                                            .postId(UUID.randomUUID().toString())
                                            .description(command.description())
                                            .postCode(code)
                                            .build();
                                    return postRepository.save(post);
                                });
                }


    @Override
    public Mono<Post> update(PostUpdatedCommand command) {
        return postRepository.findById(command.postId())
                .flatMap(post ->
                {
                    post.setDescription(command.description());
                    return postRepository.save(post);
                });
    }
}
