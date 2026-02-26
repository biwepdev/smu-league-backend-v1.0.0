package com.smu.Post.query.api.dto;

import com.smu.Post.query.api.response.PostResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

@Schema(name = "All Lookup Post Response")
public record AllLookupPostResponse(
        boolean success, List<PostResponse> post) implements Serializable {
}
