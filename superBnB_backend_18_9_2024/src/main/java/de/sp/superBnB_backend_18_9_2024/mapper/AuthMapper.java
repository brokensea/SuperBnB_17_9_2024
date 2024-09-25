package de.sp.superBnB_backend_18_9_2024.mapper;

import de.sp.superBnB_backend_18_9_2024.dtos.request.AuthRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.AuthResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Benutzer;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    // 从 AuthRequestDto 转换为 Benutzer 实体
    public Benutzer toEntity(AuthRequestDto dto) {
        Benutzer benutzer = new Benutzer();
        benutzer.setId(dto.id()); // 设置用户ID，如果是新用户可能为null
        benutzer.setName(dto.name()); // 设置用户名称
        benutzer.setEmail(dto.email()); // 设置用户邮箱
        benutzer.setPassword(dto.password()); // 注意：密码将在服务层加密
        benutzer.setRolle(dto.rolle()); // 设置用户角色
        return benutzer;
    }

    // 从 Benutzer 实体转换为 AuthResponseDto
    public AuthResponseDto toResponseDto(Benutzer benutzer) {
        return new AuthResponseDto(
                benutzer.getId(), // 返回用户ID
                benutzer.getName(), // 返回用户名
                benutzer.getEmail(), // 返回用户邮箱
                benutzer.getRolle() // 返回用户角色
        );
    }
}