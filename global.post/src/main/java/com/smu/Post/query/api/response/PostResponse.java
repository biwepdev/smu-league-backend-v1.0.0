package com.smu.Post.query.api.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(name = "Post Response")
public record PostResponse(String postId,
                           String postCode,
                           String description) implements Serializable {
}
