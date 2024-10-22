package de.sp.superBnB_backend_18_9_2024.services.impl;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BenutzerCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.BenutzerResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Benutzer;
import de.sp.superBnB_backend_18_9_2024.entities.Rolle;
import de.sp.superBnB_backend_18_9_2024.mapper.BenutzerMapper;
import de.sp.superBnB_backend_18_9_2024.repositories.BenutzerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BenutzerServiceImplTest2 {

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
    void createUser_Success() {
        // 1. Arrange
        BenutzerCreateRequestDto createRequestDto = new BenutzerCreateRequestDto(
                "Max Mustermann",
                "max.mustermann@example.com",
                "password123",
                "USER"
        );

        // 创建一个用户实体
        Benutzer benutzer = new Benutzer();
        benutzer.setId(1L); // 设置 ID
        benutzer.setName(createRequestDto.name()); // 使用 DTO 的值
        benutzer.setEmail(createRequestDto.email());
        benutzer.setRolle(Rolle.valueOf(createRequestDto.rolle().toUpperCase()));

        // 创建保存的用户实体
        Benutzer savedBenutzer = new Benutzer();
        savedBenutzer.setId(1L); // 设置 ID
        savedBenutzer.setName(benutzer.getName());
        savedBenutzer.setEmail(benutzer.getEmail());
        savedBenutzer.setRolle(benutzer.getRolle());

        // 预期的响应 DTO
        BenutzerResponseDto expectedResponseDto = new BenutzerResponseDto(
                savedBenutzer.getId(),
                savedBenutzer.getName(),
                savedBenutzer.getEmail(),
                savedBenutzer.getRolle().toString()
        );

        // 设置 Mockito 的行为
        when(mockMapper.toEntity(createRequestDto)).thenReturn(benutzer);
        when(mockBenutzerRepository.save(benutzer)).thenReturn(savedBenutzer);
        when(mockMapper.toResponseDto(savedBenutzer)).thenReturn(expectedResponseDto);

        // 2. Act
        BenutzerResponseDto response = benutzerService.createUser(createRequestDto);

        // 3. Assert
        verify(mockMapper, times(1)).toEntity(createRequestDto);
        verify(mockBenutzerRepository, times(1)).save(benutzer);
        verify(mockMapper, times(1)).toResponseDto(savedBenutzer);

        // 检查返回的响应 DTO 是否符合预期
        assertNotNull(response);
        assertEquals(expectedResponseDto.id(), response.id());
        assertEquals(expectedResponseDto.name(), response.name());
        assertEquals(expectedResponseDto.email(), response.email());
        assertEquals(expectedResponseDto.rolle(), response.rolle());
    }

    @Test
    void createUser_WithInvalidData() {
        // 1. Arrange
        BenutzerCreateRequestDto createRequestDto = new BenutzerCreateRequestDto(
                "", // Invalid name
                "invalid-email", // Invalid email
                "", // Invalid password
                "USER"
        );

        // 2. Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            benutzerService.createUser(createRequestDto);
        });
    }

    @Test
    void deleteUser_Success() {
        // 1. Arrange
        Long userId = 1L;

        // 2. Act
        benutzerService.deleteUser(userId);

        // 3. Assert
        verify(mockBenutzerRepository, times(1)).deleteById(userId);
    }

    @Test
    void deleteUser_NotFound() {
        // 1. Arrange
        Long userId = 999L; // Assume this user doesn't exist

        // 2. Act & Assert
        assertDoesNotThrow(() -> {
            benutzerService.deleteUser(userId);
        });
    }

}