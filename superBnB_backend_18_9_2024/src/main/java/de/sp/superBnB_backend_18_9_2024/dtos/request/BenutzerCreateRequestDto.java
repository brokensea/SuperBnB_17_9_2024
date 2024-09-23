package de.sp.superBnB_backend_18_9_2024.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BenutzerCreateRequestDto(
        @NotBlank String name,
        @Email @NotBlank String email,
        @NotBlank String password,
        @NotNull(message = "Role cannot be null")
        String rolle
) {
}