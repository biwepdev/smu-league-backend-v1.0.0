package com.smu.user.core.query;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Get By Code Query")
public record GetByCodeQuery(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9-]{6,40}$", message = "doit avoir de 6 a 40 caracteres lettres majuscules et les chiffres")
        String code
) implements Serializable {
}
