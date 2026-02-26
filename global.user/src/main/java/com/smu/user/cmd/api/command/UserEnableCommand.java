package com.smu.user.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "user enable command")
public record UserEnableCommand(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9]{6,20}", message = "doit avoir de 6 a 20 caracteres(les lettres majuscules et les chiffres)")
        String code
) implements Serializable {
}
