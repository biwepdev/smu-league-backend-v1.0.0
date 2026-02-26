package com.smu.Post.cmd.api.command;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Tag(name = "post")
public record PostCreatedCommand(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L} -]{3,35}", message = "doit avoir de 3 a 35 lettres alphabetiques")
        String description
) implements Serializable {
}
