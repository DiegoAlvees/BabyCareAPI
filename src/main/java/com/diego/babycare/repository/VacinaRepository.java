package com.diego.babycare.repository;

import com.diego.babycare.domain.model.Vacina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {
    List<Vacina> findByBabyIdAndStatusTrue(Long babyId);
    List<Vacina> findByBabyIdAndStatusFalse(Long babyId);
}
