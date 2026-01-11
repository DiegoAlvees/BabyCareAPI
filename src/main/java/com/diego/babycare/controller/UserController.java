package com.diego.babycare.controller;

import com.diego.babycare.domain.model.Baby;
import com.diego.babycare.domain.model.User;
import com.diego.babycare.service.BabyService;
import com.diego.babycare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.diego.babycare.service.RotinaService;
import com.diego.babycare.service.VacinaService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BabyService babyService;
    private final RotinaService rotinaService;
    private final VacinaService vacinaService;

    @PostMapping
    public ResponseEntity<User> criar(@RequestBody User user) {
        User novoUser = userService.criarUser(user);
        return ResponseEntity.ok(novoUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorId(@PathVariable Long id) {
        return userService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/completo")
    public ResponseEntity<?> buscarUsuarioCompleto(@PathVariable Long id) {
        var userOpt = userService.buscarPorId(id);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOpt.get();

        // Busca todos os bebês do usuário
        List<Baby> babies = babyService.listarPorUsuario(id);

        // Para cada bebê, busca rotinas e vacinas
        List<Map<String, Object>> babiesCompletos = babies.stream().map(baby -> {
            Map<String, Object> babyData = new HashMap<>();
            babyData.put("id", baby.getId());
            babyData.put("nome", baby.getNome());
            babyData.put("dataNascimento", baby.getDataNascimento());
            babyData.put("rotinas", rotinaService.listarPorBaby(baby.getId()));
            babyData.put("vacinasTomadas", vacinaService.listarTomadas(baby.getId()));
            babyData.put("vacinasFuturas", vacinaService.listarFuturas(baby.getId()));
            return babyData;
        }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("nome", user.getNome());
        response.put("email", user.getEmail());
        response.put("babies", babiesCompletos);

        return ResponseEntity.ok(response);
    }
}
