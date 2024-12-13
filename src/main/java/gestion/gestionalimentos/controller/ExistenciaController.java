package gestion.gestionalimentos.controller;

import gestion.gestionalimentos.entity.Existencia;
import gestion.gestionalimentos.service.ExistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/existencias")
public class ExistenciaController {

    @Autowired
    private ExistenciaService existenciaService;

    @GetMapping
    public List<Existencia> getAllExistencias() {
        return existenciaService.getAllExistencias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Existencia> getExistenciaById(@PathVariable Long id) {
        Optional<Existencia> existencia = existenciaService.getExistenciaById(id);
        return existencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Existencia createExistencia(@RequestBody Existencia existencia) {
        return existenciaService.saveExistencia(existencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Existencia> updateExistencia(@PathVariable Long id, @RequestBody Existencia existencia) {
        if (existenciaService.getExistenciaById(id).isPresent()) {
            existencia.setId(id);
            return ResponseEntity.ok(existenciaService.saveExistencia(existencia));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExistencia(@PathVariable Long id) {
        if (existenciaService.getExistenciaById(id).isPresent()) {
            existenciaService.deleteExistencia(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}