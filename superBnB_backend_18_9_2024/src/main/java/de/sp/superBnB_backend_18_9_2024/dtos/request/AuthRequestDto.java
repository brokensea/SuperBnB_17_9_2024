package de.sp.superBnB_backend_18_9_2024.dtos.request;

import de.sp.superBnB_backend_18_9_2024.entities.Rolle;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthRequestDto(
        Long id, // 用户ID
        @NotBlank String name, // 用户名
        @Email @NotBlank String email, // 用户邮箱
        @NotBlank String password, // 用户密码
        @NotNull(message = "Role cannot be null") Rolle rolle // 用户角色
) {
}