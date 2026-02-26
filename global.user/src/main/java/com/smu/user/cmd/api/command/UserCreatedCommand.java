package com.smu.user.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Tag(name = "user")
public record UserCreatedCommand(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L} -]{2,40}$", message = "doit avoir de 2 a 40 caracteres alphabetiques")
        String fullName,

        @NotNull(message = "Required")
        @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "doit etre valide")
        @Schema(description = "Telephone number")
        String telephone,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "doit etre un email valide")
        @Schema(description = "User email")
        String email,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9-]{6,40}$", message = "doit avoir de 6 a 40 caracteres lettres majuscules et les chiffres")
        @Schema(description = "Member code")
        String memberCode,

        @Pattern(regexp = "^[A-Z0-9-]{6,40}$", message = "doit avoir de 6 a 40 caracteres lettres majuscules et les chiffres")
        @Schema(description = "Staff code")
        String staffCode,

        @NotNull(message = "Required")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "doit avoir au moins 8 caracteres, un majuscule, un miniscule, un chiffre et un caractere special")
        String password,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9-]{6,40}$", message = "doit avoir de 6 a 40 caracteres lettres majuscules et les chiffres")
        @Schema(description = "role code")
        String roleCode,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L} -]{2,40}$", message = "doit avoir de 2 a 40 caracteres alphabetiques")
        String roleName
) implements Serializable {
}
