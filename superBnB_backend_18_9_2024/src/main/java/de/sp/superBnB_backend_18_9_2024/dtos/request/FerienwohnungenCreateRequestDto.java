package de.sp.superBnB_backend_18_9_2024.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FerienwohnungenCreateRequestDto(
        @NotBlank String adresse,
        @NotBlank String stadt,
        @NotNull Double preisProNacht,
        @NotNull Boolean verfuegbarkeit
) {
}
