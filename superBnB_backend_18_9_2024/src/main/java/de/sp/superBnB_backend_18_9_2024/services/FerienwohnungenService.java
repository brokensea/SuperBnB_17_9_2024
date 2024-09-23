package de.sp.superBnB_backend_18_9_2024.services;

import de.sp.superBnB_backend_18_9_2024.dtos.request.FerienwohnungenCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.FerienwohnungenResponseDto;

import java.util.List;

public interface FerienwohnungenService {
    List<FerienwohnungenResponseDto> getAllProperties();

    FerienwohnungenResponseDto addProperty(FerienwohnungenCreateRequestDto ferienwohnungenCreateRequestDto);

    FerienwohnungenResponseDto updateProperty(Long id, FerienwohnungenCreateRequestDto ferienwohnungenCreateRequestDto);

    void deleteProperty(Long id);
}