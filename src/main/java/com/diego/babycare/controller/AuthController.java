package com.diego.babycare.controller;

import com.diego.babycare.domain.model.User;
import com.diego.babycare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User request) {
        System.out.println("📥 [AuthController] Tentativa de login: " + request.getEmail());

        var userOptional = userService.buscarPorEmail(request.getEmail());

        if (userOptional.isEmpty() || !userOptional.get().getSenhaHash().equals(request.getSenhaHash())) {
            System.out.println("❌ [AuthController] Login falhou: credenciais inválidas");
            return ResponseEntity.status(401).body(Map.of("error", "Email ou senha inválidos"));
        }

        User user = userOptional.get();

        Map<String, Object> response = new HashMap<>();
        response.put("email", user.getEmail());
        response.put("nome", user.getNome());
        response.put("userId", user.getId());

        System.out.println("✅ [AuthController] Login bem-sucedido: " + user.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        System.out.println("📥 [AuthController] Tentativa de registro: " + user.getEmail());

        if (userService.buscarPorEmail(user.getEmail()).isPresent()) {
            System.out.println("❌ [AuthController] Email já cadastrado");
            return ResponseEntity.badRequest().body(Map.of("error", "Email já cadastrado"));
        }

        User createdUser = userService.criarUser(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Usuário criado com sucesso!");
        response.put("email", createdUser.getEmail());
        response.put("userId", createdUser.getId());
        response.put("nome", createdUser.getNome());

        System.out.println("✅ [AuthController] Usuário registrado: " + createdUser.getEmail());
        return ResponseEntity.ok(response);
    }
}