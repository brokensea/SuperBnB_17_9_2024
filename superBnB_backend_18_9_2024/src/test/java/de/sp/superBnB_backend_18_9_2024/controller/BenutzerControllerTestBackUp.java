/*
package de.sp.superBnB_backend_18_9_2024.controller;

import de.sp.superBnB_backend_18_9_2024.dtos.response.BenutzerResponseDto;
import de.sp.superBnB_backend_18_9_2024.services.BenutzerService;
import de.sp.superBnB_backend_18_9_2024.services.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BenutzerController.class)
class BenutzerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BenutzerService benutzerService;

    @MockBean
    private TokenService tokenService;

    private BenutzerResponseDto benutzerResponseDto;
    private String adminJwtToken;
    private String userJwtToken;

    @BeforeEach
    void setUp() {
        // 初始化用户返回数据
        benutzerResponseDto = new BenutzerResponseDto(1L, "Max Mustermann", "max@example.com", "ADMIN");

        // 模拟 Authentication 对象
        Authentication adminAuth = Mockito.mock(Authentication.class);
        Authentication userAuth = Mockito.mock(Authentication.class);


        // 模拟管理员的权限和身份信息
        Mockito.when(adminAuth.getName()).thenReturn("admin@example.com");
        Mockito.when(adminAuth.getAuthorities())
                .thenReturn(Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))); // 返回 List

        // 模拟普通用户的权限和身份信息
        Mockito.when(userAuth.getName()).thenReturn("user@example.com");
        Mockito.when(userAuth.getAuthorities())
                .thenReturn(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
        // 使用 tokenService 生成 JWT Token
        adminJwtToken = tokenService.generateToken(adminAuth);
        userJwtToken = tokenService.generateToken(userAuth);
    }

    @Test
    void shouldReturnListOfUsersWithJwtToken() throws Exception {
        // 模拟服务返回的数据
        List<BenutzerResponseDto> users = Arrays.asList(benutzerResponseDto);
        Mockito.when(benutzerService.getAllUsers()).thenReturn(users);

        // 发起带有 JWT Token 的 GET 请求并验证响应
        mockMvc.perform(get("/api/v1/users")
                        .header("Authorization", "Bearer " + adminJwtToken)) // 添加 JWT Token 到请求头
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Max Mustermann"));
    }

    @Test
    void shouldDeleteUserWithJwtToken() throws Exception {
        // 发起带有 JWT Token 的 DELETE 请求
        mockMvc.perform(delete("/api/v1/users/{id}", 1L)
                        .header("Authorization", "Bearer " + adminJwtToken)) // 添加 JWT Token 到请求头
                .andExpect(status().isNoContent());  // 期望返回状态码为 204 No Content

        // 验证 benutzerService 的 deleteUser 方法是否被调用，且传入的参数为 1L
        Mockito.verify(benutzerService).deleteUser(1L);
    }

    @Test
    void shouldReturn403WhenNonAdminRoleAccessesAdminApiWithJwtToken() throws Exception {
        // 普通用户尝试访问管理员 API
        mockMvc.perform(get("/api/v1/users")
                        .header("Authorization", "Bearer " + userJwtToken)) // 添加用户的 JWT Token
                .andExpect(status().isForbidden());  // 期望返回 403 禁止访问
    }

    @Test
    void shouldForbidAccessForNonAdminUsersWithJwtToken() throws Exception {
        // 普通用户尝试访问管理员 API
        mockMvc.perform(get("/api/v1/users")
                        .header("Authorization", "Bearer " + userJwtToken)) // 添加用户的 JWT Token
                .andExpect(status().isForbidden()); // 期望返回 403 禁止访问
    }
}*/
