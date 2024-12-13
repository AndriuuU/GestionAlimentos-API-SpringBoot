package gestion.gestionalimentos.controller;

import gestion.gestionalimentos.entity.Alimento;
import gestion.gestionalimentos.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @GetMapping
    public List<Alimento> getAllAlimentos() {
        return alimentoService.getAllAlimentos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> getAlimentoById(@PathVariable Long id) {
        Optional<Alimento> alimento = alimentoService.getAlimentoById(id);
        return alimento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Alimento createAlimento(@RequestBody Alimento alimento) {
        return alimentoService.saveAlimento(alimento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alimento> updateAlimento(@PathVariable Long id, @RequestBody Alimento alimento) {
        if (alimentoService.getAlimentoById(id).isPresent()) {
            alimento.setId(id);
            return ResponseEntity.ok(alimentoService.saveAlimento(alimento));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlimento(@PathVariable Long id) {
        if (alimentoService.getAlimentoById(id).isPresent()) {
            alimentoService.deleteAlimento(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}