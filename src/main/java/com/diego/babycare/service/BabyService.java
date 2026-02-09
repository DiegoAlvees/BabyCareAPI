package com.diego.babycare.service;

import com.diego.babycare.domain.model.Baby;
import com.diego.babycare.repository.BabyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BabyService {

    private final BabyRepository babyRepository;


    public Baby criarBaby(Baby baby) {
        return babyRepository.save(baby);
    }


    public List<Baby> listarPorUsuario(Long userId) {
        if (userId == null) {
            return listarTodos();
        }
        return babyRepository.findByUserId(userId);
    }


    public Optional<Baby> buscarPorId(Long id) {
        return babyRepository.findById(id);
    }


    public void deletarBaby(Long babyId) {
        babyRepository.deleteById(babyId);
    }


    public List<Baby> listarTodos() {
        return babyRepository.findAll();
    }
}
