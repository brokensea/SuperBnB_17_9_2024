package de.sp.superBnB_backend_18_9_2024.dtos.response;

import de.sp.superBnB_backend_18_9_2024.entities.Rolle;

public record AuthResponseDto(
        Long id, // 用户ID
        String name, // 用户名
        String email, // 用户邮箱
        Rolle rolle // 用户角色
) {
}