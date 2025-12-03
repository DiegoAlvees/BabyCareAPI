package com.diego.babycare.service;

import com.diego.babycare.domain.model.Vacina;
import com.diego.babycare.repository.VacinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacinaService {

    private final VacinaRepository vacinaRepository;

    public Vacina adicionarVacina(Vacina vacina) {
        return vacinaRepository.save(vacina);
    }

    public List<Vacina> listarTomadas(Long babyId) {
        return vacinaRepository.findByBabyIdAndStatusTrue(babyId);
    }

    public List<Vacina> listarFuturas(Long babyId) {
        return vacinaRepository.findByBabyIdAndStatusFalse(babyId);
    }

    public void deletarVacina(Long vacinaId) {
        vacinaRepository.deleteById(vacinaId);
    }
}
