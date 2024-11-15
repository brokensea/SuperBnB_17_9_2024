package de.sp.superBnB_backend_18_9_2024.controller;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BenutzerCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.BenutzerResponseDto;
import de.sp.superBnB_backend_18_9_2024.services.BenutzerService;
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

class BenutzerControllerTest {

    @Mock
    private static BenutzerService mockBenutzerService;

    @InjectMocks
    private BenutzerController benutzerController;

    private static BenutzerCreateRequestDto exampleBenutzerCreateRequestDto;
    private static BenutzerResponseDto exampleBenutzerResponseDto;

    // 创建一个干净的测试环境
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeAll
    public static void setUpBeforeClass() {
        exampleBenutzerCreateRequestDto = new BenutzerCreateRequestDto(
                "Max Mustermann",
                "max@mustermann.de",
                "password123",   // 添加密码
                "USER"            // 添加角色
        );

        exampleBenutzerResponseDto = new BenutzerResponseDto(
                1L,
                "Max Mustermann",
                "max@mustermann.de",
                "USER"            // 添加角色
        );
    }

    // 获取所有用户
    @Test
    public void testGetAllUsers_ReturnListOfUsers() {
        // (1) Arrange
        List<BenutzerResponseDto> users = List.of(exampleBenutzerResponseDto);
        when(mockBenutzerService.getAllUsers()).thenReturn(users);

        // (2) Act
        ResponseEntity<List<BenutzerResponseDto>> response = benutzerController.getAllUsers();

        // (3) Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
        verify(mockBenutzerService, times(1)).getAllUsers();
    }


    // 删除用户
    @Test
    public void testDeleteUser_Successfully() {
        // (1) Arrange
        Long userId = 1L;
        doNothing().when(mockBenutzerService).deleteUser(userId);

        // (2) Act
        ResponseEntity<Void> response = benutzerController.deleteUser(userId);

        // (3) Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(mockBenutzerService, times(1)).deleteUser(userId);
    }
}