package de.sp.superBnB_backend_18_9_2024.services.impl;

import de.sp.superBnB_backend_18_9_2024.dtos.request.FerienwohnungenCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.FerienwohnungenResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Ferienwohnungen;
import de.sp.superBnB_backend_18_9_2024.mapper.FerienwohnungenMapper;
import de.sp.superBnB_backend_18_9_2024.repositories.FerienwohnungenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class FerienwohnungenServiceImplTest {

    @InjectMocks
    private FerienwohnungenServiceImpl ferienwohnungenService;

    @Mock
    private FerienwohnungenRepository ferienwohnungenRepository;

    @Mock
    private FerienwohnungenMapper mapper;

    private FerienwohnungenCreateRequestDto createRequestDto;
    private FerienwohnungenResponseDto responseDto;
    private Ferienwohnungen entity;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // 初始化测试数据
        createRequestDto = new FerienwohnungenCreateRequestDto(
                "Musterstraße 1",
                "Musterstadt",
                100.0,
                true
        );

        responseDto = new FerienwohnungenResponseDto(
                1L,
                "Musterstraße 1",
                "Musterstadt",
                100.0,
                true
        );

        entity = new Ferienwohnungen();
        entity.setId(1L);
        entity.setAdresse("Musterstraße 1");
        entity.setStadt("Musterstadt");
        entity.setPreisProNacht(100.0);
        entity.setVerfuegbarkeit(true);
    }

    @Test
    void testGetAllProperties() {
        List<Ferienwohnungen> properties = new ArrayList<>();
        properties.add(entity);

        when(ferienwohnungenRepository.findAll()).thenReturn(properties);
        when(mapper.toResponseDto(entity)).thenReturn(responseDto);

        List<FerienwohnungenResponseDto> result = ferienwohnungenService.getAllProperties();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(responseDto, result.get(0));
        verify(ferienwohnungenRepository, times(1)).findAll();
    }

    @Test
    void testAddProperty() {
        when(mapper.toEntity(createRequestDto)).thenReturn(entity);
        when(ferienwohnungenRepository.save(entity)).thenReturn(entity);
        when(mapper.toResponseDto(entity)).thenReturn(responseDto);

        FerienwohnungenResponseDto result = ferienwohnungenService.addProperty(createRequestDto);

        assertNotNull(result);
        assertEquals(responseDto, result);
        verify(mapper, times(1)).toEntity(createRequestDto);
        verify(ferienwohnungenRepository, times(1)).save(entity);
    }

    @Test
    void testUpdateProperty() {
        Long propertyId = 1L;
        when(mapper.toEntity(createRequestDto)).thenReturn(entity);
        when(ferienwohnungenRepository.save(entity)).thenReturn(entity);
        when(mapper.toResponseDto(entity)).thenReturn(responseDto);

        FerienwohnungenResponseDto result = ferienwohnungenService.updateProperty(propertyId, createRequestDto);

        assertNotNull(result);
        assertEquals(responseDto, result);
        verify(mapper, times(1)).toEntity(createRequestDto);
        verify(ferienwohnungenRepository, times(1)).save(entity);
    }

    @Test
    void testDeleteProperty() {
        Long propertyId = 1L;

        ferienwohnungenService.deleteProperty(propertyId);

        verify(ferienwohnungenRepository, times(1)).deleteById(propertyId);
    }
}