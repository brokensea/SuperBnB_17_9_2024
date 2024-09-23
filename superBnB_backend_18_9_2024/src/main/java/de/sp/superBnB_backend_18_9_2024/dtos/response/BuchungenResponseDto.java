package de.sp.superBnB_backend_18_9_2024.dtos.response;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BuchungenResponseDto(
        Long id,
        @NotNull Long benutzerId,
        @NotNull Long ferienwohnungId,
        @NotNull LocalDate buchungsdatum,
        @NotNull LocalDate checkinDatum,
        @NotNull LocalDate checkoutDatum
) {
}