package de.sp.superBnB_backend_18_9_2024.mapper;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BuchungenCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.BuchungenResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Buchungen;
import org.springframework.stereotype.Component;

@Component
public class BuchungenMapper {

    public Buchungen toEntity(BuchungenCreateRequestDto dto) {
        Buchungen buchung = new Buchungen();
        // 这里需要从 Benutzer 和 Ferienwohnungen 中查找相应的对象
        // 假设有对应的 repository 可用
        // Benutzer benutzer = benutzerRepository.findById(dto.benutzerId()).orElseThrow();
        // Ferienwohnungen ferienwohnung = ferienwohnungenRepository.findById(dto.ferienwohnungId()).orElseThrow();
        // buchung.setBenutzer(benutzer);
        // buchung.setFerienwohnung(ferienwohnung);

        buchung.setCheckinDatum(dto.checkinDatum());
        buchung.setCheckoutDatum(dto.checkoutDatum());
        return buchung;
    }

    public BuchungenResponseDto toResponseDto(Buchungen buchung) {
        return new BuchungenResponseDto(buchung.getId(),
                buchung.getBenutzer().getId(),
                buchung.getFerienwohnung().getId(),
                buchung.getBuchungsdatum(),
                buchung.getCheckinDatum(),
                buchung.getCheckoutDatum());
    }
}