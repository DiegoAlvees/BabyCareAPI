package com.diego.babycare.controller;

import com.diego.babycare.domain.model.Baby;
import com.diego.babycare.domain.model.User;
import com.diego.babycare.repository.UserRepository;
import com.diego.babycare.service.BabyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/babies")
@RequiredArgsConstructor
public class BabyController {
    private final BabyService babyService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> criarBaby(@RequestBody Map<String, Object> request) {
        try {
            String nome = (String) request.get("nome");
            String dataNascimento = (String) request.get("dataNascimento");

            // Pega o userId do objeto user aninhado
            Map<String, Object> userMap = (Map<String, Object>) request.get("user");
            Integer userIdInt = (Integer) userMap.get("id");
            Long userId = userIdInt.longValue();

            // Busca o usuário
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + userId));

            // Cria o bebê
            Baby baby = new Baby();
            baby.setNome(nome);
            baby.setDataNascimento(java.time.LocalDate.parse(dataNascimento));
            baby.setUser(user);

            Baby novoBaby = babyService.criarBaby(baby);
            return ResponseEntity.ok(novoBaby);

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Erro ao criar bebê: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Baby>> listarBabiesDoUsuario(@RequestParam(required = false) Long userId) {
        List<Baby> babies;
        if (userId != null) {
            babies = babyService.listarPorUsuario(userId);
        } else {
            babies = babyService.listarTodos();
        }
        return ResponseEntity.ok(babies);
    }

    @DeleteMapping("/{babyId}")
    public ResponseEntity<Void> deletarBaby(@PathVariable Long babyId) {
        babyService.deletarBaby(babyId);
        return ResponseEntity.noContent().build();
    }
}