package de.sp.superBnB_backend_18_9_2024.dtos.response;

public record BenutzerResponseDto(
        Long id,
        String name,
        String email,
        String rolle
) {
}
