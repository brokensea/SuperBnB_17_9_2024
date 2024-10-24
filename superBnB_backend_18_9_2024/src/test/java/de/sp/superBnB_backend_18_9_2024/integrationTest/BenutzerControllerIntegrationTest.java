package de.sp.superBnB_backend_18_9_2024.integrationTest;

import com.jayway.jsonpath.JsonPath;
import de.sp.superBnB_backend_18_9_2024.entities.Benutzer;
import de.sp.superBnB_backend_18_9_2024.entities.Rolle;
import de.sp.superBnB_backend_18_9_2024.repositories.BenutzerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BenutzerControllerIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BenutzerRepository benutzerRepository;

    private static String adminToken;

    @BeforeEach
    public void setUp() throws Exception {
        // 清空数据库
        benutzerRepository.deleteAll();

        // 注册并登录管理员
        registerAdmin();
    }

    private void registerAdmin() throws Exception {
        String registerJson = "{ \"email\": \"admin@mail.com\", \"password\": \"admin\", \"role\": \"ADMIN\" }";
        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerJson))
                .andExpect(status().isOk());

        loginAdmin();
    }

    private void loginAdmin() throws Exception {
        String username = "admin@mail.com";
        String password = "admin";
        String auth = username + ":" + password;
        String encodeAuth = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        MvcResult result = mockMvc.perform(post("/api/auth/signin")
                        .header("Authorization", encodeAuth)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // 提取 token
        String jsonResponse = result.getResponse().getContentAsString();
        adminToken = JsonPath.parse(jsonResponse).read("$.token");
        assertNotNull(adminToken, "Admintoken darf nicht null sein ");
    }

    @Test
    public void testGetAllUsers_Successfully() throws Exception {
        // 创建用户以进行测试
        Benutzer user1 = new Benutzer();
        user1.setName("User One");
        user1.setEmail("user1@mail.com");
        user1.setPassword("password");
        user1.setRolle(Rolle.USER);
        user1.setBuchungen(new ArrayList<>()); // 初始化 Buchungen 列表

        Benutzer user2 = new Benutzer();
        user2.setName("User Two");
        user2.setEmail("user2@mail.com");
        user2.setPassword("password");
        user2.setRolle(Rolle.USER);
        user2.setBuchungen(new ArrayList<>()); // 初始化 Buchungen 列表
        benutzerRepository.saveAll(List.of(user1, user2));

        mockMvc.perform(get("/api/v1/users")
                        .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("user1@mail.com"))
                .andExpect(jsonPath("$[1].email").value("user2@mail.com"));
    }

    @Test
    public void testDeleteUser_Successfully() throws Exception {
        // 创建用户以进行测试
        Benutzer user = new Benutzer();
        user.setName("User Name"); // 设置用户名
        user.setEmail("user@mail.com"); // 设置邮箱
        user.setPassword("password"); // 设置密码
        user.setRolle(Rolle.USER); // 设置角色
        user.setBuchungen(new ArrayList<>()); // 初始化 Buchungen 列表

        benutzerRepository.save(user);

        mockMvc.perform(delete("/api/v1/users/" + user.getId())
                        .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isNoContent());

        assertEquals(0, benutzerRepository.count());
    }

    @Test
    public void testDeleteUser_NotFound() throws Exception {
        // 尝试删除不存在的用户
        mockMvc.perform(delete("/api/v1/users/" + 999L)
                        .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isNotFound());
    }
}