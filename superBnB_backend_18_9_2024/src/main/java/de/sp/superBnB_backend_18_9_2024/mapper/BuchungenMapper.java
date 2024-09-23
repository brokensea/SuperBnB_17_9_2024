package de.sp.superBnB_backend_18_9_2024.mapper;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BuchungenCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.BuchungenResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Buchungen;
import org.springframework.stereotype.Component;

@Component
public class BuchungenMapper {

    public Buchungen toEntity(BuchungenCreateRequestDto dto) {
        Buchungen buchung = new Buchungen();
        // 查找 Benutzer 和 Ferienwohnungen 对象
        // Benutzer benutzer = benutzerRepository.findById(dto.benutzerId()).orElseThrow();
        // Ferienwohnungen ferienwohnung = ferienwohnungenRepository.findById(dto.ferienwohnungId()).orElseThrow();
        // buchung.setBenutzer(benutzer);
        // buchung.setFerienwohnung(ferienwohnung);

        // 使用 DTO 中的 buchungsdatum
        buchung.setBuchungsdatum(dto.buchungsdatum()); // 可以根据需要设置为当前日期或来自 DTO 的日期
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