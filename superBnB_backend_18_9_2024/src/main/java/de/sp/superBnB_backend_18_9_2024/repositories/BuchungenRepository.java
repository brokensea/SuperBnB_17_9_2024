package de.sp.superBnB_backend_18_9_2024.repositories;


import de.sp.superBnB_backend_18_9_2024.entities.Buchungen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuchungenRepository extends JpaRepository<Buchungen, Long> {
    // 可以添加自定义查询方法，例如按 Benutzer 查询 Buchungen
}