package de.sp.superBnB_backend_18_9_2024.mapper;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BenutzerCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.BenutzerResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Benutzer;
import de.sp.superBnB_backend_18_9_2024.entities.Rolle;
import org.springframework.stereotype.Component;

@Component
public class BenutzerMapper {

    public Benutzer toEntity(BenutzerCreateRequestDto dto) {
        Benutzer benutzer = new Benutzer();
        benutzer.setName(dto.name());
        benutzer.setEmail(dto.email());
        benutzer.setPassword(dto.password());
        benutzer.setRolle(Rolle.valueOf(dto.rolle())); // 确保 rolle 是有效的
        return benutzer;
    }

    public BenutzerResponseDto toResponseDto(Benutzer benutzer) {
        return new BenutzerResponseDto(benutzer.getId(), benutzer.getName(), benutzer.getEmail(), benutzer.getRolle().name());
    }
}