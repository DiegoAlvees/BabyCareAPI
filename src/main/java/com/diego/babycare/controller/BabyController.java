package com.diego.babycare.controller;

import com.diego.babycare.domain.model.Baby;
import com.diego.babycare.service.BabyService;
import com.diego.babycare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/babies")
@RequiredArgsConstructor
public class BabyController {

    private final BabyService babyService;
    private final UserService userService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<Baby> criarBaby(@PathVariable Long userId, @RequestBody Baby baby) {
        var user = userService.buscarPorId(userId);
        if(user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        baby.setUser(user.get());
        Baby novoBaby = babyService.criarBaby(baby);

        return ResponseEntity.ok(novoBaby);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Baby>> listarPorUsuario(@PathVariable Long userId) {
        return ResponseEntity.ok(babyService.listarPorUsuario(userId));
    }

    @DeleteMapping("/{babyId}")
    public ResponseEntity<Void> deletarBaby(@PathVariable Long babyId) {

        var existe = babyService.buscarPorId(babyId);
        if (existe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        babyService.deletarBaby(babyId);
        return ResponseEntity.noContent().build();
    }

}
