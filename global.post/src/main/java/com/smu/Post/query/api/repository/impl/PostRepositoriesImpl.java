package com.smu.Post.query.api.repository.impl;

import com.smu.Post.core.model.Post;
import com.smu.Post.query.api.repository.PostRepositories;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class PostRepositoriesImpl implements PostRepositories {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<Post> getLastElement() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "postCode"));
        query.limit(1);

        return reactiveMongoTemplate.find(query, Post.class)
                .next();
    }
}
