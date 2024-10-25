package de.sp.superBnB_backend_18_9_2024.services.impl;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BenutzerCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.BenutzerResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Benutzer;
import de.sp.superBnB_backend_18_9_2024.mapper.BenutzerMapper;
import de.sp.superBnB_backend_18_9_2024.repositories.BenutzerRepository;
import de.sp.superBnB_backend_18_9_2024.services.BenutzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class BenutzerServiceImpl implements BenutzerService {

    @Autowired
    private BenutzerRepository benutzerRepository;

    @Autowired
    private BenutzerMapper mapper;

    @Override
    public List<BenutzerResponseDto> getAllUsers() {
        return benutzerRepository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BenutzerResponseDto createUser(BenutzerCreateRequestDto benutzerCreateRequestDto) {
        if (benutzerCreateRequestDto.name().isEmpty() || benutzerCreateRequestDto.email().isEmpty() || benutzerCreateRequestDto.password().isEmpty()) {
            throw new IllegalArgumentException("Name, email, and password cannot be empty");
        }
        Benutzer benutzer = mapper.toEntity(benutzerCreateRequestDto);
        Benutzer savedBenutzer = benutzerRepository.save(benutzer);
        return mapper.toResponseDto(savedBenutzer);
    }

    public boolean existsById(Long id) {
        return benutzerRepository.existsById(id);
    }

    @Override
    public void deleteUser(Long id) {
        benutzerRepository.deleteById(id);
    }
}