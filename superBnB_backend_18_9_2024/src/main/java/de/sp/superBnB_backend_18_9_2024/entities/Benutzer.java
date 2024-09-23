package de.sp.superBnB_backend_18_9_2024.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Benutzer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rolle rolle;

    @OneToMany(mappedBy = "benutzer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Buchungen> buchungen = new ArrayList<>();

    // Getters and Setters


    public Benutzer() {
    }

    public Benutzer(Long id, String name, String email, String password, Rolle rolle, List<Buchungen> buchungen) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rolle = rolle;
        this.buchungen = buchungen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Rolle getRolle() {
        return rolle;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    public List<Buchungen> getBuchungen() {
        return buchungen;
    }

    public void setBuchungen(List<Buchungen> buchungen) {
        this.buchungen = buchungen;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(rolle.name())); // 使用角色名称
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}