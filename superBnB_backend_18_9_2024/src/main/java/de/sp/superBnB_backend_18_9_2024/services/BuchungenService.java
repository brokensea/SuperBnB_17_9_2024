package de.sp.superBnB_backend_18_9_2024.services;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BuchungenCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.BuchungenResponseDto;

import java.util.List;

public interface BuchungenService {
    List<BuchungenResponseDto> getAllBookings();

    BuchungenResponseDto bookProperty(BuchungenCreateRequestDto buchungenCreateRequestDto);

    void cancelBooking(Long id);
}