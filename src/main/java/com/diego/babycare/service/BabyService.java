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

    // Cria um bebê
    public Baby criarBaby(Baby baby) {
        return babyRepository.save(baby);
    }

    // Lista todos os bebês de um usuário específico (recebido pelo frontend)
    public List<Baby> listarPorUsuario(Long userId) {
        if (userId == null) {
            return listarTodos();
        }
        return babyRepository.findByUserId(userId);
    }

    // Busca bebê por ID
    public Optional<Baby> buscarPorId(Long id) {
        return babyRepository.findById(id);
    }

    // Deleta bebê por ID
    public void deletarBaby(Long babyId) {
        babyRepository.deleteById(babyId);
    }

    // Lista todos os bebês (sem filtro de usuário)
    public List<Baby> listarTodos() {
        return babyRepository.findAll();
    }
}
