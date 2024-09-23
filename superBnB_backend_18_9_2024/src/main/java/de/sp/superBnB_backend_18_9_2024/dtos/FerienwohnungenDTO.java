package de.sp.superBnB_backend_18_9_2024.dtos;

public record FerienwohnungenDTO(
        Long id,
        String adresse,
        String stadt,
        Double preisProNacht,
        Boolean verfuegbarkeit
) {
}

