package com.smu.Post.query.api.controller;

import com.smu.Post.core.query.GetByCodeQuery;
import com.smu.Post.query.api.dto.AllLookupPostResponse;
import com.smu.Post.query.api.dto.LookupPostResponse;
import com.smu.Post.query.api.handler.PostQueryHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/post/post-lookup/")
@Tag(name = "post")
public class LookupPostController {
    private final PostQueryHandler postQueryHandler;

    @Operation(summary = "Retrieve data post")
    @GetMapping(path = "/get-post", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AllLookupPostResponse> getPost() {
        return postQueryHandler.findPosts()
                .collectList()
                .map(list ->new AllLookupPostResponse(true, list));
    }

    @Operation(summary = "Retrieve data post by code")
    @PutMapping(path = "/get-post-by-post-ode", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LookupPostResponse> getPostByCode(@Valid @RequestBody GetByCodeQuery query)
    {
        return postQueryHandler.findByCode(query)
                .map(postResponse -> new LookupPostResponse(true, postResponse));
    }
}
