package de.sp.superBnB_backend_18_9_2024.mapper;

import de.sp.superBnB_backend_18_9_2024.dtos.request.AuthRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.AuthResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Benutzer;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    // 从 AuthRequestDto 转换到 Benutzer 实体
    public Benutzer toEntity(AuthRequestDto dto) {
        Benutzer benutzer = new Benutzer();
        benutzer.setEmail(dto.email());
        benutzer.setPassword(dto.password()); // 注意：密码应该在存储前进行加密
        benutzer.setRolle(dto.role());
        return benutzer;
    }

    // 从 Benutzer 实体转换到 AuthResponseDto
    public AuthResponseDto toResponseDto(Benutzer benutzer) {
        return new AuthResponseDto(
                benutzer.getId(),
                benutzer.getEmail(),
                benutzer.getRolle()
        );
    }
}