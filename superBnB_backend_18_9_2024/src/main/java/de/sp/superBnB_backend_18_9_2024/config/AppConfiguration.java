package de.sp.superBnB_backend_18_9_2024.config;

import de.sp.superBnB_backend_18_9_2024.repositories.BenutzerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfiguration {
    private final BenutzerRepository benutzerRepository;

    public AppConfiguration(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> benutzerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Benutzer nicht gefunden"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}