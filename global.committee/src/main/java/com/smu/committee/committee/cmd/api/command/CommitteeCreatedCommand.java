package com.smu.committee.committee.cmd.api.command;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Tag(name = "committee")
public record CommitteeCreatedCommand(
        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9]{6,20}", message = "doit avoir de 5 a 20 caracteres(les lettres majuscules et les chiffres)")
        String memberCode,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L} -]{3,35}", message = "doit avoir de 3 a 35 lettres alphabetiques")
        String memberName,

        @NotNull(message = "Required")
        @Pattern(regexp = "^\\d{4,20}$", message = "doit avoir des caracteres numeriques")
        String memberCitizenId,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9]{6,20}", message = "doit avoir de 5 a 20 caracteres(les lettres majuscules et les chiffres)")
        String villageCode,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L}0-9 '+\\-()]{2,100}$", message = "doit avoir de 2 a 100 caracteres alphabetiques")
        String villageName,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[A-Z0-9]{5,20}", message = "doit avoir de 5 a 20 caracteres(les lettres majuscules et les chiffres)")
        String postCode,

        @NotNull(message = "Required")
        @Pattern(regexp = "^[\\p{L} '-]{3,35}", message = "doit avoir de 3 a 35 lettres alphabetiques")
        String postName
) implements Serializable {
}
