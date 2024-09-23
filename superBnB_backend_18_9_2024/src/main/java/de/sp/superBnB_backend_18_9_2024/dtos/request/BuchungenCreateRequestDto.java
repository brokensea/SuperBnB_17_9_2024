package de.sp.superBnB_backend_18_9_2024.dtos.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BuchungenCreateRequestDto(
        @NotNull Long benutzerId,
        @NotNull Long ferienwohnungId,
        @NotNull LocalDate checkinDatum,
        @NotNull LocalDate checkoutDatum
) {
}