package com.smu.village.village.cmd.api.command;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

@Schema(name = "Village Created Command")
public record VillageCreatedCommand(
        @NotNull(message = "Required")
        @Pattern(regexp = "^(?=.*\\p{L})[\\p{L}' _]{2,60}$", message = "doit avoir de 2 a 60 caracteres alphabetiques")
        @Schema(description = "village name")
        String villageName,

        @NotNull(message = "Required")
        @Schema(description = "Country")
        String country,

        @NotNull(message = "Required")
        @Schema(description = "Province")
        String province,

        @NotNull(message = "Required")
        @Schema(description = "Commune")
        String commune,

        @NotNull(message = "Required")
        @Schema(description = "Zone")
        String zone,

        @Schema(description = "Street number")
        String streetNumber,

        @Schema(description = "Street name")
        String streetName,

        @Schema(description = "Village email address")
        String email,

        @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "doit etre valide")
        @Schema(description = "Mobile number")
        String mobileNo,

        @Schema(description = "Sector activities")
        String sectorActivities

) implements Serializable {
}