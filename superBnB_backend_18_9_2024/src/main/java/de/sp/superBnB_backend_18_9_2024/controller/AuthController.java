package de.sp.superBnB_backend_18_9_2024.controller;

import de.sp.superBnB_backend_18_9_2024.dtos.request.AuthRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.AuthResponseDto;
import de.sp.superBnB_backend_18_9_2024.services.AuthentificationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthentificationService authentificationService;

    public AuthController(AuthentificationService authentificationService) {
        this.authentificationService = authentificationService;
    }

    @PostMapping("/signin")
    public String signin(Authentication authentication) {
        return authentificationService.token(authentication);
    }

    @PostMapping("/signup")
    public AuthResponseDto signup(@RequestBody AuthRequestDto dto) {
        return authentificationService.signUp(dto);
    }

    @GetMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }
}