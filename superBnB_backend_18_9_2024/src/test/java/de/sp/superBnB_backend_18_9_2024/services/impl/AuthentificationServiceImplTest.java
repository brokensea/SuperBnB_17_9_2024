package de.sp.superBnB_backend_18_9_2024.services.impl;

import de.sp.superBnB_backend_18_9_2024.dtos.request.AuthRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.AuthResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Benutzer;
import de.sp.superBnB_backend_18_9_2024.entities.Rolle;
import de.sp.superBnB_backend_18_9_2024.mapper.AuthMapper;
import de.sp.superBnB_backend_18_9_2024.repositories.BenutzerRepository;
import de.sp.superBnB_backend_18_9_2024.services.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthentificationServiceImplTest {

    @Mock
    private BenutzerRepository benutzerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;

    @Mock
    private AuthMapper authMapper;

    @InjectMocks
    private AuthentificationServiceImpl authentificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void signUp() {
        // Arrange
        AuthRequestDto requestDto = new AuthRequestDto(
                1L,            // 用户ID
                "Max",         // 用户名
                "max@example.com", // 用户邮箱
                "password123",  // 用户密码
                Rolle.USER      // 用户角色
        );

        // 创建 Benutzer 实体用于模拟行为
        Benutzer benutzer = new Benutzer();
        benutzer.setId(1L);
        benutzer.setName(requestDto.name());
        benutzer.setEmail(requestDto.email());
        benutzer.setPassword(requestDto.password());
        benutzer.setRolle(Rolle.USER);

        // 创建预期的响应 DTO
        AuthResponseDto expectedResponse = new AuthResponseDto(
                1L,
                "Max",
                "max@example.com",
                Rolle.USER
        );

        // 模拟 authMapper、passwordEncoder 和 benutzerRepository 的行为
        when(authMapper.toEntity(requestDto)).thenReturn(benutzer);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword"); // 修改这里，传入原始密码
        when(benutzerRepository.save(any(Benutzer.class))).thenReturn(benutzer);
        when(authMapper.toResponseDto(any(Benutzer.class))).thenReturn(expectedResponse);

        // Act
        AuthResponseDto actualResponse = authentificationService.signUp(requestDto);

        // Assert
        assertEquals(expectedResponse, actualResponse);
        verify(passwordEncoder).encode("password123");  // 验证编码原始密码的调用
        verify(benutzerRepository).save(benutzer);
    }


    @Test
    void token() {
        // Arrange
        Authentication mockAuthentication = mock(Authentication.class);
        when(mockAuthentication.getName()).thenReturn("max@example.com");
        String expectedToken = "generatedToken";
        when(tokenService.generateToken(mockAuthentication)).thenReturn(expectedToken);

        // Act
        String actualToken = authentificationService.token(mockAuthentication);

        // Assert
        assertEquals(expectedToken, actualToken);
        verify(tokenService).generateToken(mockAuthentication);
    }
}