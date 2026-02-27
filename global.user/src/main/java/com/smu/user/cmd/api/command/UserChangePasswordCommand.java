package com.smu.user.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Change Password Command")
public record UserChangePasswordCommand(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[a-z0-9-]{36}$",message = "L'identifiant ne doit avoir que 36 caracteres")
        String userId,

        @NotNull(message = "Required")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "doit avoir au moins 8 caracteres, un majuscule, un miniscule, un chiffre et un caractere special")
        String oldPassword,

        @NotNull(message = "Required")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "doit avoir au moins 8 caracteres, un majuscule, un miniscule, un chiffre et un caractere special")
        String newPassword,

        @NotNull(message = "Required")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "doit avoir au moins 8 caracteres, un majuscule, un miniscule, un chiffre et un caractere special")
        String confirmPassword
) implements Serializable {
}
