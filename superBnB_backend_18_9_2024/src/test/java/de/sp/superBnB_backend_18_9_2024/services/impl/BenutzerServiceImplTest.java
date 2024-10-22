package de.sp.superBnB_backend_18_9_2024.services.impl;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BenutzerCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.entities.Benutzer;
import de.sp.superBnB_backend_18_9_2024.mapper.BenutzerMapper;
import de.sp.superBnB_backend_18_9_2024.repositories.BenutzerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class BenutzerServiceImplTest {

    @Mock
    private BenutzerRepository mockBenutzerRepository;

    @Mock
    private BenutzerMapper mockMapper;

    @InjectMocks
    private BenutzerServiceImpl benutzerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers() {
        // 1. Arrange
        Benutzer benutzer1 = new Benutzer();
        Benutzer benutzer2 = new Benutzer();
        List<Benutzer> mockBenutzerList = Arrays.asList(benutzer1, benutzer2);

        when(mockBenutzerRepository.findAll()).thenReturn(mockBenutzerList);

        // 2. Act
        benutzerService.getAllUsers();

        // 3. Assert
        verify(mockBenutzerRepository, times(1)).findAll();
        verify(mockMapper, times(2)).toResponseDto(any(Benutzer.class));
    }

    @Test
    void createUser() {
        // 1. Arrange
        BenutzerCreateRequestDto createRequestDto = new BenutzerCreateRequestDto(
                "Max Mustermann",
                "max.mustermann@example.com",
                "password123",
                "USER"
        );
        Benutzer benutzer = new Benutzer();
        Benutzer savedBenutzer = new Benutzer();

        when(mockMapper.toEntity(createRequestDto)).thenReturn(benutzer);
        when(mockBenutzerRepository.save(benutzer)).thenReturn(savedBenutzer);
        when(mockMapper.toResponseDto(savedBenutzer)).thenReturn(null); // You can replace null with an actual mock response if needed

        // 2. Act
        benutzerService.createUser(createRequestDto);

        // 3. Assert
        verify(mockMapper, times(1)).toEntity(createRequestDto);
        verify(mockBenutzerRepository, times(1)).save(benutzer);
        verify(mockMapper, times(1)).toResponseDto(savedBenutzer);
    }

    @Test
    void deleteUser() {
        // 1. Arrange
        Long userId = 1L;

        // 2. Act
        benutzerService.deleteUser(userId);

        // 3. Assert
        verify(mockBenutzerRepository, times(1)).deleteById(userId);
    }
}