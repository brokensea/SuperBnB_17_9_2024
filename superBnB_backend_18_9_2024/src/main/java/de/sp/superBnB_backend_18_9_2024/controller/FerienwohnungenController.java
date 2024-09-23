package de.sp.superBnB_backend_18_9_2024.controller;

import de.sp.superBnB_backend_18_9_2024.dtos.request.FerienwohnungenCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.FerienwohnungenResponseDto;
import de.sp.superBnB_backend_18_9_2024.services.FerienwohnungenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/properties")
public class FerienwohnungenController {

    @Autowired
    private FerienwohnungenService ferienwohnungenService;


    /*   - GET /api/properties: Liste aller verfügbaren Ferienwohnungen anzeigen*/

    /*pending von verüfgbareen */
    @GetMapping
    public ResponseEntity<List<FerienwohnungenResponseDto>> getAllProperties() {
        List<FerienwohnungenResponseDto> properties = ferienwohnungenService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    /*   - POST /api/properties: Eine neue Ferienwohnung hinzufügen (nur für Administratoren)*/
    //pending Admin recht
    @PostMapping
    public ResponseEntity<FerienwohnungenResponseDto> addProperty(@RequestBody FerienwohnungenCreateRequestDto ferienwohnungenCreateRequestDto) {
        FerienwohnungenResponseDto createdProperty = ferienwohnungenService.addProperty(ferienwohnungenCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProperty);
    }

    /*  - PUT /api/properties/{id}: Eine bestehende Ferienwohnung aktualisieren (nur für Administratoren)*/
    //admin recht pending
    @PutMapping("/{id}")
    public ResponseEntity<FerienwohnungenResponseDto> updateProperty(@PathVariable Long id, @RequestBody FerienwohnungenCreateRequestDto ferienwohnungenCreateRequestDto) {
        FerienwohnungenResponseDto updatedProperty = ferienwohnungenService.updateProperty(id, ferienwohnungenCreateRequestDto);
        return ResponseEntity.ok(updatedProperty);
    }

    /* - DELETE /api/properties/{id}: Eine Ferienwohnung löschen (nur für Administratoren)*/
    //admin recht pending
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        ferienwohnungenService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }
}