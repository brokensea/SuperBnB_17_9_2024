package de.sp.superBnB_backend_18_9_2024.dtos.request;

import de.sp.superBnB_backend_18_9_2024.entities.Rolle;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthRequestDto(
        @Email @NotBlank String email,
        @NotBlank String password,
        @NotNull(message = "Role cannot be null") Rolle role // 角色字段
) {
}