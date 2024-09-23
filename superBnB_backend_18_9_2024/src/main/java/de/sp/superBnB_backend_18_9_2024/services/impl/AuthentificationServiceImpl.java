package de.sp.superBnB_backend_18_9_2024.services.impl;

import de.sp.superBnB_backend_18_9_2024.dtos.request.AuthRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.AuthResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Benutzer;
import de.sp.superBnB_backend_18_9_2024.mapper.AuthMapper;
import de.sp.superBnB_backend_18_9_2024.repositories.BenutzerRepository;
import de.sp.superBnB_backend_18_9_2024.services.AuthentificationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationServiceImpl implements AuthentificationService {
    private final BenutzerRepository benutzerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper; // 引入 Mapper

    public AuthentificationServiceImpl(BenutzerRepository benutzerRepository, PasswordEncoder passwordEncoder, AuthMapper authMapper) {
        this.benutzerRepository = benutzerRepository;
        this.passwordEncoder = passwordEncoder;
        this.authMapper = authMapper; // 初始化 Mapper
    }

    @Override
    public AuthResponseDto signUp(AuthRequestDto dto) {
        Benutzer benutzer = authMapper.toEntity(dto); // 使用 Mapper
        benutzer.setPassword(passwordEncoder.encode(benutzer.getPassword())); // 加密密码
        benutzer = benutzerRepository.save(benutzer); // 保存用户
        return authMapper.toResponseDto(benutzer); // 返回响应 DTO
    }
}