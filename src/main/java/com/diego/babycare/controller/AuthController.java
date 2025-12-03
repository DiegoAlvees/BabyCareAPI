package com.diego.babycare.controller;

import com.diego.babycare.domain.dto.LoginRequest;
import com.diego.babycare.domain.model.User;
import com.diego.babycare.service.UserService;
import com.diego.babycare.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody LoginRequest request) {
        var user = userService.buscarPorEmail(request.getEmail());

        if(user.isEmpty() || !passwordEncoder.matches(request.getSenha(), user.get().getSenhaHash())) {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }

        String token = jwtUtil.generateToken(user.get().getEmail());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userService.buscarPorEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        user.setSenhaHash(passwordEncoder.encode(user.getSenhaHash()));

        userService.criarUser(user);

        return ResponseEntity.ok("Usuário criado com sucesso!");
    }

}
