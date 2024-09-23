package de.sp.superBnB_backend_18_9_2024.services;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BenutzerCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.BenutzerResponseDto;

import java.util.List;

public interface BenutzerService {
    List<BenutzerResponseDto> getAllUsers();

    BenutzerResponseDto createUser(BenutzerCreateRequestDto benutzerCreateRequestDto);

    void deleteUser(Long id);
}