package com.smu.Role.cmd.api.command;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Tag(name = "role")
public record RoleCreatedCommand(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L} -]{3,35}", message = "doit avoir de 3 a 35 lettres alphabetiques")
        String name,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L} -]{3,35}", message = "doit avoir de 3 a 35 lettres alphabetiques")
        String description
) implements Serializable {
}
