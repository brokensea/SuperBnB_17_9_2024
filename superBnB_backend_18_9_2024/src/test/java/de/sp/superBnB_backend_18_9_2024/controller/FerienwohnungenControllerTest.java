package de.sp.superBnB_backend_18_9_2024.controller;

import de.sp.superBnB_backend_18_9_2024.dtos.request.FerienwohnungenCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.FerienwohnungenResponseDto;
import de.sp.superBnB_backend_18_9_2024.services.FerienwohnungenService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FerienwohnungenControllerTest {

    @Mock
    private FerienwohnungenService mockFerienwohnungenService;

    @InjectMocks
    private FerienwohnungenController ferienwohnungenController;

    private static FerienwohnungenCreateRequestDto exampleFerienwohnungenCreateRequestDto;
    private static FerienwohnungenResponseDto exampleFerienwohnungenResponseDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeAll
    public static void setUpBeforeClass() {
        exampleFerienwohnungenCreateRequestDto = new FerienwohnungenCreateRequestDto(
                "Mustermann Straße 1",
                "Musterstadt",
                100.0,
                true
        );

        exampleFerienwohnungenResponseDto = new FerienwohnungenResponseDto(
                1L,
                "Mustermann Straße 1",
                "Musterstadt",
                100.0,
                true
        );
    }

    // 获取所有可用的 Ferienwohnungen
    @Test
    public void testGetAllProperties_ReturnListOfProperties() {
        // Arrange
        List<FerienwohnungenResponseDto> properties = List.of(exampleFerienwohnungenResponseDto);
        when(mockFerienwohnungenService.getAllProperties()).thenReturn(properties);

        // Act
        ResponseEntity<List<FerienwohnungenResponseDto>> response = ferienwohnungenController.getAllProperties();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(properties, response.getBody());
        verify(mockFerienwohnungenService, times(1)).getAllProperties();
    }

    // 添加 Ferienwohnung
    @Test
    public void testAddProperty_Successfully() {
        // Arrange
        when(mockFerienwohnungenService.addProperty(any(FerienwohnungenCreateRequestDto.class)))
                .thenReturn(exampleFerienwohnungenResponseDto);

        // Act
        ResponseEntity<FerienwohnungenResponseDto> response = ferienwohnungenController.addProperty(exampleFerienwohnungenCreateRequestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(exampleFerienwohnungenResponseDto, response.getBody());
        verify(mockFerienwohnungenService, times(1)).addProperty(any(FerienwohnungenCreateRequestDto.class));
    }

    // 更新 Ferienwohnung
    @Test
    public void testUpdateProperty_Successfully() {
        // Arrange
        Long propertyId = 1L;
        when(mockFerienwohnungenService.updateProperty(eq(propertyId), any(FerienwohnungenCreateRequestDto.class)))
                .thenReturn(exampleFerienwohnungenResponseDto);

        // Act
        ResponseEntity<FerienwohnungenResponseDto> response = ferienwohnungenController.updateProperty(propertyId, exampleFerienwohnungenCreateRequestDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(exampleFerienwohnungenResponseDto, response.getBody());
        verify(mockFerienwohnungenService, times(1)).updateProperty(eq(propertyId), any(FerienwohnungenCreateRequestDto.class));
    }

    // 删除 Ferienwohnung
    @Test
    public void testDeleteProperty_Successfully() {
        // Arrange
        Long propertyId = 1L;
        doNothing().when(mockFerienwohnungenService).deleteProperty(propertyId);

        // Act
        ResponseEntity<Void> response = ferienwohnungenController.deleteProperty(propertyId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(mockFerienwohnungenService, times(1)).deleteProperty(propertyId);
    }
}