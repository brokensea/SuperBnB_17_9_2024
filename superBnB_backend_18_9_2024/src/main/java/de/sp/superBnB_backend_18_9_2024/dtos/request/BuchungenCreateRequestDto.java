package de.sp.superBnB_backend_18_9_2024.dtos.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record BuchungenCreateRequestDto(
        @NotNull Long benutzerId,
        @NotNull Long ferienwohnungId,
        @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate buchungsdatum,
        @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkinDatum,
        @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkoutDatum
) {
}