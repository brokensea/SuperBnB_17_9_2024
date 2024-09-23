package de.sp.superBnB_backend_18_9_2024.dtos;

import java.time.LocalDate;

public record BuchungenDTO(
        Long id,
        Long benutzerId,
        Long ferienwohnungId,
        LocalDate buchungsdatum,
        LocalDate checkinDatum,
        LocalDate checkoutDatum
) {
}