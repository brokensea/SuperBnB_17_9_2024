package de.sp.superBnB_backend_18_9_2024.controller;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BenutzerCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.BenutzerResponseDto;
import de.sp.superBnB_backend_18_9_2024.services.BenutzerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BenutzerController.class)
class BenutzerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BenutzerService benutzerService;
    private BenutzerResponseDto benutzerResponseDto;

    @BeforeEach
    void setUp() {
        // 初始化模拟的返回数据
        benutzerResponseDto = new BenutzerResponseDto(1L, "Max Mustermann", "max@example.com", "ADMIN");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
        // 模拟具有管理员权限的用户
    void shouldReturnListOfUsers() throws Exception {
        // 模拟服务返回的数据
        List<BenutzerResponseDto> users = Arrays.asList(benutzerResponseDto);
        Mockito.when(benutzerService.getAllUsers()).thenReturn(users);

        // 发起 GET 请求并验证响应
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Max Mustermann"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldCreateNewUser() throws Exception {
        // 创建请求 DTO 对象
        BenutzerCreateRequestDto requestDto = new BenutzerCreateRequestDto("Max Mustermann", "max@example.com", "password123", "ADMIN");

        // 模拟服务创建用户
        Mockito.when(benutzerService.createUser(any(BenutzerCreateRequestDto.class))).thenReturn(benutzerResponseDto);

        // 发起 POST 请求并验证响应
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"Max Mustermann\", \"email\": \"max@example.com\", \"password\": \"password123\", \"rolle\": \"ADMIN\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Max Mustermann"))
                .andExpect(jsonPath("$.email").value("max@example.com"))
                .andExpect(jsonPath("$.rolle").value("ADMIN"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldDeleteUser() throws Exception {
        // 发起 DELETE 请求
        mockMvc.perform(delete("/api/v1/users/{id}", 1L))
                .andExpect(status().isNoContent());

        // 验证服务调用
        Mockito.verify(benutzerService).deleteUser(1L);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
        // 使用普通用户身份
    void shouldReturn403WhenAccessUsersWithNonAdminRole() throws Exception {
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isForbidden());  // 应该返回 403 禁止访问
    }

    @Test
    @WithMockUser(roles = "USER")
    void shouldForbidAccessForNonAdminUsers() throws Exception {
        // 非管理员用户尝试访问管理员 API
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isForbidden());
    }
}