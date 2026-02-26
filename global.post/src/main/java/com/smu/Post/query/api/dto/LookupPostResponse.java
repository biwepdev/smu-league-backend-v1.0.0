package com.smu.Post.query.api.dto;

import com.smu.Post.query.api.response.PostResponse;

import java.io.Serializable;

public record LookupPostResponse(
        boolean success, PostResponse postResponse) implements Serializable {
}
