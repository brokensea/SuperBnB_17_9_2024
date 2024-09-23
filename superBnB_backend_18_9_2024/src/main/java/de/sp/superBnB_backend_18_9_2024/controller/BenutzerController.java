package de.sp.superBnB_backend_18_9_2024.controller;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BenutzerCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.BenutzerResponseDto;
import de.sp.superBnB_backend_18_9_2024.services.BenutzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class BenutzerController {

    @Autowired
    private BenutzerService benutzerService;

    /* - GET /api/users: Liste aller Benutzer anzeigen (nur für Administratoren)*/
    //admin recht pending
    @GetMapping
    public ResponseEntity<List<BenutzerResponseDto>> getAllUsers() {
        List<BenutzerResponseDto> users = benutzerService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /* - POST /api/users: Einen neuen Benutzer anlegen (nur für Administratoren)*/
    //admin recht pending
    @PostMapping
    public ResponseEntity<BenutzerResponseDto> createUser(@RequestBody BenutzerCreateRequestDto benutzerCreateRequestDto) {
        BenutzerResponseDto createdUser = benutzerService.createUser(benutzerCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    /*  - DELETE /api/users/{id}: Einen Benutzer löschen (nur für Administratoren)*/
    //admin recht pending
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        benutzerService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}