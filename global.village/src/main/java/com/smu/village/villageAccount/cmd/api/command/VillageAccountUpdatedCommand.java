package com.smu.village.villageAccount.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "VillageAccount Updated Command")
public record VillageAccountUpdatedCommand(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[a-z0-9-]{36}$", message = "L'identifinat ne doit avoir que 36 caracteres")
        String villageAccountId,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[0-9]{6,40}$", message = "doit contenir uniquemet des chiffres de 6 a 40")
        String accountNo,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9-]{6,40}$", message = "doit etre de 6 a 40 caracteres majuscules et les chiffres")
        String villageCode,

        @NotNull(message = "Required")
        @Pattern(regexp = "^(?=.*\\p{L})[\\p{L}' _]{2,60}$", message = "doit avoir de 2 a 60 caracteres alphabetiques")
        @Schema(description = "village name")
        String villageName,

        @NotNull(message = "Required")
        @Pattern(regexp = "^(?=.*\\p{L})[\\p{L}' _]{2,60}$", message = "doit avoir de 2 a 60 caracteres alphabetiques")
        @Schema(description = "village address")
        String villageAddress,

        @NotNull(message = "Required")
        @Pattern(regexp = "^(19|20)\\d\\d-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", message = "doit etre valide (format attendu yyyy-MM-dd)")
        @Schema(description = "date")
        String date
) implements Serializable {
}
