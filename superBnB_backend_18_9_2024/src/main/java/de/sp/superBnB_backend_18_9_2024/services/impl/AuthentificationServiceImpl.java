package de.sp.superBnB_backend_18_9_2024.services.impl;

import de.sp.superBnB_backend_18_9_2024.dtos.request.AuthRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.AuthResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Benutzer;
import de.sp.superBnB_backend_18_9_2024.mapper.AuthMapper;
import de.sp.superBnB_backend_18_9_2024.repositories.BenutzerRepository;
import de.sp.superBnB_backend_18_9_2024.services.AuthentificationService;
import de.sp.superBnB_backend_18_9_2024.services.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationServiceImpl implements AuthentificationService {
    private final BenutzerRepository benutzerRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthMapper authMapper;

    public AuthentificationServiceImpl(BenutzerRepository benutzerRepository, PasswordEncoder passwordEncoder, TokenService tokenService, AuthMapper authMapper) {
        this.benutzerRepository = benutzerRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.authMapper = authMapper;
    }

    @Override
    public AuthResponseDto signUp(AuthRequestDto dto) {
        Benutzer benutzer = authMapper.toEntity(dto);
        benutzer.setPassword(passwordEncoder.encode(benutzer.getPassword()));
        benutzer = benutzerRepository.save(benutzer);
        return authMapper.toResponseDto(benutzer);
    }

    public String token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        System.out.println("Token erstellt für " + authentication.getName());
        System.out.println("Token: " + token);
        return token;
    }
}