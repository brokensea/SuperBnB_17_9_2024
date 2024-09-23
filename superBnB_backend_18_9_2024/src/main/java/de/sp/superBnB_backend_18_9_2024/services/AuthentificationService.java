package de.sp.superBnB_backend_18_9_2024.services;

import de.sp.superBnB_backend_18_9_2024.dtos.request.AuthRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.AuthResponseDto;

public interface AuthentificationService {
    AuthResponseDto signUp(AuthRequestDto dto);
}