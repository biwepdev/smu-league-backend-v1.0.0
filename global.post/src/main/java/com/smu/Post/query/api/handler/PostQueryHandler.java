package com.smu.Post.query.api.handler;

import com.smu.Post.core.query.GetByCodeQuery;
import com.smu.Post.query.api.response.PostResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostQueryHandler {
    Flux<PostResponse> findPosts();
    Mono<PostResponse> findByCode(GetByCodeQuery query);
}
