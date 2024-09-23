package de.sp.superBnB_backend_18_9_2024.services.impl;

import de.sp.superBnB_backend_18_9_2024.dtos.request.BuchungenCreateRequestDto;
import de.sp.superBnB_backend_18_9_2024.dtos.response.BuchungenResponseDto;
import de.sp.superBnB_backend_18_9_2024.entities.Benutzer;
import de.sp.superBnB_backend_18_9_2024.entities.Buchungen;
import de.sp.superBnB_backend_18_9_2024.entities.Ferienwohnungen;
import de.sp.superBnB_backend_18_9_2024.mapper.BuchungenMapper;
import de.sp.superBnB_backend_18_9_2024.repositories.BenutzerRepository;
import de.sp.superBnB_backend_18_9_2024.repositories.BuchungenRepository;
import de.sp.superBnB_backend_18_9_2024.repositories.FerienwohnungenRepository;
import de.sp.superBnB_backend_18_9_2024.services.BuchungenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BuchungenServiceImpl implements BuchungenService {

    @Autowired
    private BuchungenRepository buchungenRepository;
    @Autowired
    private BenutzerRepository benutzerRepository;
    @Autowired
    private FerienwohnungenRepository ferienwohnungenRepository;

    @Autowired
    private BuchungenMapper mapper;

    @Override
    public List<BuchungenResponseDto> getAllBookings() {
        return buchungenRepository.findAll().stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BuchungenResponseDto bookProperty(BuchungenCreateRequestDto buchungenCreateRequestDto) {
        // 查找相关的 Benutzer 和 Ferienwohnungen 实体
        Benutzer benutzer = benutzerRepository.findById(buchungenCreateRequestDto.benutzerId())
                .orElseThrow(() -> new NoSuchElementException("Benutzer mit der Id: " + buchungenCreateRequestDto.benutzerId() + " nicht vorhanden"));

        Ferienwohnungen ferienwohnung = ferienwohnungenRepository.findById(buchungenCreateRequestDto.ferienwohnungId())
                .orElseThrow(() -> new NoSuchElementException("Ferienwohnung mit der Id: " + buchungenCreateRequestDto.ferienwohnungId() + " nicht vorhanden"));

        // 创建新的 Buchungen 实体
        Buchungen buchung = new Buchungen();
        buchung.setBenutzer(benutzer);
        buchung.setFerienwohnung(ferienwohnung);
        buchung.setBuchungsdatum(buchungenCreateRequestDto.buchungsdatum());
        buchung.setCheckinDatum(buchungenCreateRequestDto.checkinDatum());
        buchung.setCheckoutDatum(buchungenCreateRequestDto.checkoutDatum());

        // 保存 Buchungen 实体


        //Buchungen savedBuchung = buchungenRepository.save(buchung);
        benutzer.getBuchungen().add(buchung);
        benutzerRepository.save(benutzer);

        // 返回 BuchungenResponseDto
        //return mapper.toResponseDto(savedBuchung);
        return mapper.toResponseDto(buchung);
    }

    @Override
    public void cancelBooking(Long id) {
        buchungenRepository.deleteById(id);
    }
}