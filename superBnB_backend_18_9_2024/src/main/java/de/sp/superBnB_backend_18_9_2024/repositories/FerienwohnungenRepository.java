package de.sp.superBnB_backend_18_9_2024.repositories;

import de.sp.superBnB_backend_18_9_2024.entities.Ferienwohnungen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FerienwohnungenRepository extends JpaRepository<Ferienwohnungen, Long> {
    // 可以添加自定义查询方法，例如按城市或价格过滤
}