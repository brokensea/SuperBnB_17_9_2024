package de.sp.superBnB_backend_18_9_2024.services;

import de.sp.superBnB_backend_18_9_2024.dtos.request.AuthRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.AuthResponseDto;
import org.springframework.security.core.Authentication;

public interface AuthentificationService {
    AuthResponseDto signUp(AuthRequestDto dto);

    String token(Authentication authentication);
}