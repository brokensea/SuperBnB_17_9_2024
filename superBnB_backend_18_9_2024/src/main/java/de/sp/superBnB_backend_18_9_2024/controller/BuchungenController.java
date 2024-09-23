package de.sp.superBnB_backend_18_9_2024.controller;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BuchungenCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.BuchungenResponseDto;
import de.sp.superBnB_backend_18_9_2024.services.BuchungenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BuchungenController {

    @Autowired
    private BuchungenService buchungenService;

    /*   - GET /api/bookings: Liste aller Buchungen anzeigen (nur für Administratoren)*/
    //admin recht pending
    @GetMapping
    public ResponseEntity<List<BuchungenResponseDto>> getAllBookings() {
        List<BuchungenResponseDto> bookings = buchungenService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    /* - POST /api/bookings: Eine Ferienwohnung buchen*/
    @PostMapping
    public ResponseEntity<BuchungenResponseDto> bookProperty(@RequestBody BuchungenCreateRequestDto buchungenCreateRequestDto) {
        BuchungenResponseDto createdBooking = buchungenService.bookProperty(buchungenCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    /* - DELETE /api/bookings/{id}: Eine Buchung stornieren*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        buchungenService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }
}
