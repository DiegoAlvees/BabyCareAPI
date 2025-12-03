package com.diego.babycare.controller;

import com.diego.babycare.domain.model.Vacina;
import com.diego.babycare.service.BabyService;
import com.diego.babycare.service.VacinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacinas")
@RequiredArgsConstructor
public class VacinaController {

    private final VacinaService vacinaService;
    private final BabyService babyService;

    @PostMapping("baby/{babyId}")
    public ResponseEntity<Vacina> criar(@PathVariable Long babyId, @RequestBody Vacina vacina) {
        var baby = babyService.buscarPorId(babyId);
        if(baby.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        vacina.setBaby(baby.get());
        Vacina nova = vacinaService.adicionarVacina(vacina);

        return ResponseEntity.ok(nova);
    }

    @GetMapping("/baby/{babyId}/tomadas")
    public ResponseEntity<List<Vacina>> tomadas(@PathVariable Long babyId) {
        return ResponseEntity.ok(vacinaService.listarTomadas(babyId));
    }

    @GetMapping("/baby/{babyId}/futuras")
    public ResponseEntity<List<Vacina>> futuras(@PathVariable Long babyId) {
        return ResponseEntity.ok(vacinaService.listarFuturas(babyId));
    }

    @DeleteMapping("/{vacinaId}")
    public ResponseEntity<Void> deletarVacina(@PathVariable Long vacinaId) {
        vacinaService.deletarVacina(vacinaId);
        return ResponseEntity.noContent().build();
    }
}
