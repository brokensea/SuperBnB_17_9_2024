package de.sp.superBnB_backend_18_9_2024.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Buchungen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "benutzer_id", nullable = false)
    private Benutzer benutzer;

    @ManyToOne
    @JoinColumn(name = "ferienwohnung_id", nullable = false)
    private Ferienwohnungen ferienwohnung;

    @Column(nullable = false)
    private LocalDate buchungsdatum;

    @Column(nullable = false)
    private LocalDate checkinDatum;

    @Column(nullable = false)
    private LocalDate checkoutDatum;

    public Buchungen() {
    }

    public Buchungen(Long id, Benutzer benutzer, Ferienwohnungen ferienwohnung, LocalDate buchungsdatum, LocalDate checkinDatum, LocalDate checkoutDatum) {
        this.id = id;
        this.benutzer = benutzer;
        this.ferienwohnung = ferienwohnung;
        this.buchungsdatum = buchungsdatum;
        this.checkinDatum = checkinDatum;
        this.checkoutDatum = checkoutDatum;
    }
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Ferienwohnungen getFerienwohnung() {
        return ferienwohnung;
    }

    public void setFerienwohnung(Ferienwohnungen ferienwohnung) {
        this.ferienwohnung = ferienwohnung;
    }

    public LocalDate getBuchungsdatum() {
        return buchungsdatum;
    }

    public void setBuchungsdatum(LocalDate buchungsdatum) {
        this.buchungsdatum = buchungsdatum;
    }

    public LocalDate getCheckinDatum() {
        return checkinDatum;
    }

    public void setCheckinDatum(LocalDate checkinDatum) {
        this.checkinDatum = checkinDatum;
    }

    public LocalDate getCheckoutDatum() {
        return checkoutDatum;
    }

    public void setCheckoutDatum(LocalDate checkoutDatum) {
        this.checkoutDatum = checkoutDatum;
    }
}