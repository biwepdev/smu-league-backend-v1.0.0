package com.smu.village.villageAccount.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Village Account Enable Command")
public record VillageAccountEnableCommand(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9-]{6,15}", message = "doit avoir au moins 6 caracteres(les lettres majuscules et les chiffres)")
        String villageAccountCode
) implements Serializable {
}
