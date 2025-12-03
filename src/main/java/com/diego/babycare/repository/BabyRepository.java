package com.diego.babycare.repository;

import com.diego.babycare.domain.model.Baby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BabyRepository extends JpaRepository<Baby, Long> {
    List<Baby> findByUserId(Long userId);
}
