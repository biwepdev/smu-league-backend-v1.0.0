package com.smu.Post.query.api.handler;

import com.smu.Post.cmd.api.command.PostCreatedCommand;
import com.smu.Post.cmd.api.command.PostUpdatedCommand;
import com.smu.Post.core.model.Post;
import reactor.core.publisher.Mono;

public interface PostEventHandler {
    Mono<Post> create(PostCreatedCommand command);
    Mono<Post> update(PostUpdatedCommand command);
}
