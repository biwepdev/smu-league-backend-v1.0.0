package com.smu.Post.cmd.api.command;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Tag(name = "post")
public record PostUpdatedCommand(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[a-z0-9-]{36}", message = "l'identifiant doit avoir 36 caracteres")
        String postId,


        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L} -]{3,35}", message = "doit avoir de 3 a 35 lettres alphabetiques")
        String description
) implements Serializable {
}
