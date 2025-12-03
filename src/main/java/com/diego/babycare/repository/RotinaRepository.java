package com.diego.babycare.repository;

import com.diego.babycare.domain.model.Rotina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RotinaRepository extends JpaRepository<Rotina, Long> {
    List<Rotina> findByBabyId(Long babyId);
}
