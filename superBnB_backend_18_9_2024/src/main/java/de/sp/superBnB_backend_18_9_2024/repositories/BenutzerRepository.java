package de.sp.superBnB_backend_18_9_2024.repositories;

import de.sp.superBnB_backend_18_9_2024.entities.Benutzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
    // 可以添加自定义查询方法，例如按 email 查询 Benutzer
    Optional<Benutzer> findByEmail(String email);
}