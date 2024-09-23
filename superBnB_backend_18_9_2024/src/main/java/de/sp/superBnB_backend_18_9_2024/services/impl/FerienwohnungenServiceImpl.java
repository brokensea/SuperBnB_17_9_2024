package de.sp.superBnB_backend_18_9_2024.services.impl;

import de.sp.superBnB_backend_18_9_2024.dtos.request.FerienwohnungenCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.FerienwohnungenResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Ferienwohnungen;
import de.sp.superBnB_backend_18_9_2024.mapper.FerienwohnungenMapper;
import de.sp.superBnB_backend_18_9_2024.repositories.FerienwohnungenRepository;
import de.sp.superBnB_backend_18_9_2024.services.FerienwohnungenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FerienwohnungenServiceImpl implements FerienwohnungenService {

    @Autowired
    private FerienwohnungenRepository ferienwohnungenRepository;

    @Autowired
    private FerienwohnungenMapper mapper;

    @Override
    public List<FerienwohnungenResponseDto> getAllProperties() {
        return ferienwohnungenRepository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public FerienwohnungenResponseDto addProperty(FerienwohnungenCreateRequestDto ferienwohnungenCreateRequestDto) {
        Ferienwohnungen ferienwohnung = mapper.toEntity(ferienwohnungenCreateRequestDto);
        Ferienwohnungen savedFerienwohnung = ferienwohnungenRepository.save(ferienwohnung);
        return mapper.toResponseDto(savedFerienwohnung);
    }

    @Override
    public FerienwohnungenResponseDto updateProperty(Long id, FerienwohnungenCreateRequestDto ferienwohnungenCreateRequestDto) {
        Ferienwohnungen ferienwohnung = mapper.toEntity(ferienwohnungenCreateRequestDto);
        ferienwohnung.setId(id);
        Ferienwohnungen updatedFerienwohnung = ferienwohnungenRepository.save(ferienwohnung);
        return mapper.toResponseDto(updatedFerienwohnung);
    }

    @Override
    public void deleteProperty(Long id) {
        ferienwohnungenRepository.deleteById(id);
    }
}