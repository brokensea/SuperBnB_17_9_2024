package de.sp.superBnB_backend_18_9_2024.dtos.response;

public record FerienwohnungenResponseDto(
        Long id,
        String adresse,
        String stadt,
        Double preisProNacht,
        Boolean verfuegbarkeit
) {
}