package com.smu.Post.query.api.repository;

import com.smu.Post.core.model.Post;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PostRepositories {
  Mono<Post> getLastElement();
}
