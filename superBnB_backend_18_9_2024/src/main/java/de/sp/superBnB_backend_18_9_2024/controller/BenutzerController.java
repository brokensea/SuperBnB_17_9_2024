package de.sp.superBnB_backend_18_9_2024.controller;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BenutzerCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.BenutzerResponseDto;
import de.sp.superBnB_backend_18_9_2024.services.BenutzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class BenutzerController {

    @Autowired
    private BenutzerService benutzerService;

    /*    - GET /api/v1/users: Liste aller Benutzer anzeigen (nur für Administratoren) */
    @PreAuthorize("hasAuthority('ADMIN')")  // 仅管理员权限
    @GetMapping
    public ResponseEntity<List<BenutzerResponseDto>> getAllUsers() {
        List<BenutzerResponseDto> users = benutzerService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /* - POST /api/v1/users: Einen neuen Benutzer anlegen (nur für Administratoren) */
    @PreAuthorize("hasAuthority('ADMIN')")  // 仅管理员权限
    @PostMapping
    public ResponseEntity<BenutzerResponseDto> createUser(@RequestBody BenutzerCreateRequestDto benutzerCreateRequestDto) {
        BenutzerResponseDto createdUser = benutzerService.createUser(benutzerCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /*  - DELETE /api/v1/users/{id}: Einen Benutzer löschen (nur für Administratoren) */
    @PreAuthorize("hasAuthority('ADMIN')")  // 仅管理员权限
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        benutzerService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}