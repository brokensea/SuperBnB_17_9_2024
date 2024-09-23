package de.sp.superBnB_backend_18_9_2024.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ferienwohnungen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String stadt;

    @Column(nullable = false)
    private Double preisProNacht;

    @Column(nullable = false)
    private Boolean verfuegbarkeit;

    @OneToMany(mappedBy = "ferienwohnung", cascade = CascadeType.ALL)
    private List<Buchungen> buchungen = new ArrayList<>();

    public Ferienwohnungen() {
    }

    public Ferienwohnungen(Long id, String adresse, String stadt, Double preisProNacht, Boolean verfuegbarkeit, List<Buchungen> buchungen) {
        this.id = id;
        this.adresse = adresse;
        this.stadt = stadt;
        this.preisProNacht = preisProNacht;
        this.verfuegbarkeit = verfuegbarkeit;
        this.buchungen = buchungen;
    }
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public Double getPreisProNacht() {
        return preisProNacht;
    }

    public void setPreisProNacht(Double preisProNacht) {
        this.preisProNacht = preisProNacht;
    }

    public Boolean getVerfuegbarkeit() {
        return verfuegbarkeit;
    }

    public void setVerfuegbarkeit(Boolean verfuegbarkeit) {
        this.verfuegbarkeit = verfuegbarkeit;
    }

    public List<Buchungen> getBuchungen() {
        return buchungen;
    }

    public void setBuchungen(List<Buchungen> buchungen) {
        this.buchungen = buchungen;
    }
}
