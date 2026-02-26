package com.smu.Post.query.api.repository;

import com.smu.Post.core.model.Post;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PostRepository extends ReactiveMongoRepository<Post, String > {
    Mono<Boolean> existsPostByDescription(@Param("description") String description);
    Mono<Post> findByPostCode(@Param("postCode") String postCode);
    @Query(value ="{}",sort = "{'post_code':-1}")
    Flux<Post> findByCodeDesc();
}
