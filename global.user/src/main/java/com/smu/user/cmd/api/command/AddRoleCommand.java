package com.smu.user.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

public record AddRoleCommand(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9-]{6,40}$",message = "doit avoir de 6 a 40 caracteres des lettres majuscules et les chiffres")
        String userCode,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9-]{6,40}$", message = "doit avoir de 6 a 40 caracteres lettres majuscules et les chiffres")
        @Schema(description = "role code")
        String roleCode,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L} -]{2,40}$", message = "doit avoir de 2 a 40 caracteres alphabetiques")
        String roleName
) implements Serializable {
}
