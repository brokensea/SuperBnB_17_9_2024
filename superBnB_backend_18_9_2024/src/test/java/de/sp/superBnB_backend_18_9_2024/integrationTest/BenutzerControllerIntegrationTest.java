package de.sp.superBnB_backend_18_9_2024.integrationTest;

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
        benutzerRepository.deleteAll();
        registerAdmin();
    }

    private void registerAdmin() throws Exception {
        String registerJson = "{ \"name\": \"Admin\", \"email\": \"admin@mail.com\", \"password\": \"admin\", \"rolle\": \"ADMIN\" }";
        mockMvc.perform(post("/api/v1/auth/signup")
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

        MvcResult result = mockMvc.perform(post("/api/v1/auth/signin")
                        .header("Authorization", encodeAuth)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // 获取纯文本的 JWT token
        adminToken = result.getResponse().getContentAsString();
        assertNotNull(adminToken, "Admin token should not be null");
    }


    @Test
    public void testGetAllUsers_Successfully() throws Exception {
        Benutzer user1 = new Benutzer(null, "User One", "user1@mail.com", "password", Rolle.USER, List.of());
        Benutzer user2 = new Benutzer(null, "User Two", "user2@mail.com", "password", Rolle.USER, List.of());
        benutzerRepository.saveAll(List.of(user1, user2));

        mockMvc.perform(get("/api/v1/users")
                        .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("admin@mail.com"))
                .andExpect(jsonPath("$[1].email").value("user1@mail.com"))
                .andExpect(jsonPath("$[2].email").value("user2@mail.com"));
    }

    @Test
    public void testDeleteUser_Successfully() throws Exception {
        Benutzer user = new Benutzer(null, "User Name", "user@mail.com", "password", Rolle.USER, List.of());
        benutzerRepository.save(user);

        mockMvc.perform(delete("/api/v1/users/" + user.getId())
                        .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isNoContent());

        assertEquals(1, benutzerRepository.count(), "The user repository should be empty after deletion");
    }

    @Test
    public void testDeleteUser_NotFound() throws Exception {
        mockMvc.perform(delete("/api/v1/users/" + 999L)
                        .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isNotFound());
    }
}