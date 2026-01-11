package com.diego.babycare.service;

import com.diego.babycare.domain.model.Rotina;
import com.diego.babycare.repository.RotinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RotinaService {

    private final RotinaRepository rotinaRepository;

    public Rotina criarRotina(Rotina rotina) {
        return rotinaRepository.save(rotina);
    }

    public List<Rotina> listarPorBaby(Long babyId) {
        return rotinaRepository.findByBabyId(babyId);
    }

    public Optional<Rotina> buscarPorId(Long id) {
        return rotinaRepository.findById(id);
    }

    public void deletarRotina (Long rotinaId) {
        rotinaRepository.deleteById(rotinaId);
    }
}
