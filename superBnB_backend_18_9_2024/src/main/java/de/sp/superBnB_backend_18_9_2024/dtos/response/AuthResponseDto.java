package de.sp.superBnB_backend_18_9_2024.dtos.response;

import de.sp.superBnB_backend_18_9_2024.entities.Rolle;

public record AuthResponseDto(
        Long id,
        String email,
        Rolle role // 角色字段
) {
}