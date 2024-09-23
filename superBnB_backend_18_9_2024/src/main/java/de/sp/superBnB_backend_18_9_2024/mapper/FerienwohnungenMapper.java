package de.sp.superBnB_backend_18_9_2024.mapper;

import de.sp.superBnB_backend_18_9_2024.dtos.request.FerienwohnungenCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.FerienwohnungenResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Ferienwohnungen;
import org.springframework.stereotype.Component;

@Component
public class FerienwohnungenMapper {

    public Ferienwohnungen toEntity(FerienwohnungenCreateRequestDto dto) {
        Ferienwohnungen ferienwohnung = new Ferienwohnungen();
        ferienwohnung.setAdresse(dto.adresse());
        ferienwohnung.setStadt(dto.stadt());
        ferienwohnung.setPreisProNacht(dto.preisProNacht());
        ferienwohnung.setVerfuegbarkeit(dto.verfuegbarkeit());
        return ferienwohnung;
    }

    public FerienwohnungenResponseDto toResponseDto(Ferienwohnungen ferienwohnung) {
        return new FerienwohnungenResponseDto(ferienwohnung.getId(),
                ferienwohnung.getAdresse(),
                ferienwohnung.getStadt(),
                ferienwohnung.getPreisProNacht(),
                ferienwohnung.getVerfuegbarkeit());
    }
}