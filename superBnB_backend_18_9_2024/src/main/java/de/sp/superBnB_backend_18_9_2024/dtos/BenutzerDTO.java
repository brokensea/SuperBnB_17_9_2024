package de.sp.superBnB_backend_18_9_2024.dtos;

import de.sp.superBnB_backend_18_9_2024.entities.Rolle;

public record BenutzerDTO(
        Long id,
        String name,
        String email,
        String password,
        Rolle rolle
) {
}