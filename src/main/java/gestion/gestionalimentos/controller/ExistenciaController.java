package gestion.gestionalimentos.controller;

import gestion.gestionalimentos.entity.Alimento;
import gestion.gestionalimentos.entity.Existencia;
import gestion.gestionalimentos.entity.Ubicacion;
import gestion.gestionalimentos.repository.AlimentoRepository;
import gestion.gestionalimentos.repository.UbicacionRepository;
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
    @Autowired
    private AlimentoRepository alimentoRepository;
    @Autowired
    private UbicacionRepository ubicacionRepository;

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
        // Verifica que el alimento esté presente
        if (existencia.getAlimento() == null || existencia.getAlimento().getId() == null) {
            throw new IllegalArgumentException("El alimento no puede ser nulo o no debe tener un ID nulo");
        }

        // Verifica que la ubicacion esté presente
        if (existencia.getUbicacion() == null || existencia.getUbicacion().getId() == null) {
            throw new IllegalArgumentException("La ubicación no puede ser nula o no debe tener un ID nulo");
        }

        // Carga el Alimento desde la base de datos
        Alimento alimento = alimentoRepository.findById(existencia.getAlimento().getId())
                .orElseThrow(() -> new RuntimeException("Alimento no encontrado"));

        // Carga la Ubicacion desde la base de datos
        Ubicacion ubicacion = ubicacionRepository.findById(existencia.getUbicacion().getId())
                .orElseThrow(() -> new RuntimeException("Ubicacion no encontrada"));

        // Asigna los objetos cargados a la entidad Existencia
        existencia.setAlimento(alimento);
        existencia.setUbicacion(ubicacion);

        // Guarda la entidad Existencia
        return existenciaService.saveExistencia(existencia);
    }


    @PutMapping("/{id}")
    public Existencia updateExistencia(@PathVariable Long id, @RequestBody Existencia existencia) {
        return existenciaService.updateExistencia(id, existencia);
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