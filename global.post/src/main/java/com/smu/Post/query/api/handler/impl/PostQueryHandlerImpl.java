package com.smu.Post.query.api.handler.impl;

import com.smu.Post.core.model.Post;
import com.smu.Post.core.query.GetByCodeQuery;
import com.smu.Post.query.api.handler.PostQueryHandler;
import com.smu.Post.query.api.repository.PostRepository;
import com.smu.Post.query.api.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PostQueryHandlerImpl implements PostQueryHandler {
    private final PostRepository postRepository;
    @Override
    public Flux<PostResponse> findPosts() {
        return postRepository.findAll()
                .flatMap(post -> Mono.just(getPost(post)));
    }

    @Override
    public Mono<PostResponse> findByCode(GetByCodeQuery query) {
        return postRepository.findByPostCode(query.code())
                .map(this::getPost);
    }

    private PostResponse getPost(Post post) {
        return new PostResponse(
                post.getPostId(),
                post.getPostCode(),
                post.getDescription());
    }
}
