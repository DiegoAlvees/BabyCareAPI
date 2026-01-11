package com.diego.babycare.controller;


import com.diego.babycare.domain.model.Rotina;
import com.diego.babycare.service.BabyService;
import com.diego.babycare.service.RotinaService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/babies/{babyId}/rotinas")
@RequiredArgsConstructor
public class RotinaController {

    private final RotinaService rotinaService;
    private final BabyService babyService;

    @PostMapping
    public ResponseEntity<Rotina> criar(@PathVariable Long babyId, @RequestBody Rotina rotina) {
        var baby = babyService.buscarPorId(babyId);
        if(baby.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        rotina.setBaby(baby.get());
        Rotina nova = rotinaService.criarRotina(rotina);

        return ResponseEntity.ok(nova);
    }

    @GetMapping
    public ResponseEntity<List<Rotina>> listarPorBaby(@PathVariable Long babyId) {
        var rotinas = rotinaService.listarPorBaby(babyId);
        return ResponseEntity.ok(rotinas);
    }

    @DeleteMapping("/{rotinaId}")
    public ResponseEntity<Void> deletarRotina(
            @PathVariable Long babyId,  // ← ADICIONE ISSO
            @PathVariable Long rotinaId) {

        // Opcional: validar se a rotina pertence ao bebê
        var rotina = rotinaService.buscarPorId(rotinaId);
        if (rotina.isEmpty() || !rotina.get().getBaby().getId().equals(babyId)) {
            return ResponseEntity.notFound().build();
        }

        rotinaService.deletarRotina(rotinaId);
        return ResponseEntity.noContent().build();
    }

}
