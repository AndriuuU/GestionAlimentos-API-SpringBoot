package gestion.gestionalimentos.controller;

import gestion.gestionalimentos.entity.Alimento;
import gestion.gestionalimentos.entity.Ubicacion;
import gestion.gestionalimentos.service.AlimentoService;
import gestion.gestionalimentos.service.ExistenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alimentos")
public class AlimentoController {

    @Autowired
    private ExistenciaService existenciaService;
    @Autowired
    private AlimentoService alimentoService;


    @GetMapping
    public ResponseEntity<List<Alimento>> getAllAlimentos() {
        try {
            List<Alimento> alimentos = alimentoService.getAllAlimentos();
            return ResponseEntity.ok(alimentos);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching alimentos", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimento> getAlimentoById(@PathVariable Long id) {
        try {
            Optional<Alimento> alimento = alimentoService.getAlimentoById(id);
            return alimento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching alimento by id", e);
        }
    }

    @PostMapping
    public ResponseEntity<Alimento> createAlimento(@Valid @RequestBody Alimento alimento) {
        try {
            // Validación adicional si es necesaria
            if (alimento.getFechaCaducidad() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fecha de caducidad es obligatoria");
            }

            Alimento savedAlimento = alimentoService.saveAlimento(alimento);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAlimento);
        } catch (ResponseStatusException e) {
            throw e; // Pasar excepciones conocidas
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating alimento", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alimento> updateAlimento(@PathVariable Long id, @RequestBody Alimento alimento) {
        try {
            // Validación adicional si es necesaria
            if (alimento.getFechaCaducidad() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fecha de caducidad es obligatoria");
            }

            Alimento updatedAlimento = alimentoService.updateAlimento(id, alimento);
            return ResponseEntity.ok(updatedAlimento);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Si no existe el recurso
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating alimento", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlimento(@PathVariable Long id) {
        try {
            alimentoService.deleteAlimento(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Si no existe el recurso
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting alimento", e);
        }
    }

    @GetMapping("/{alimentoId}/sugerir-ubicacion")
    public Ubicacion sugerirUbicacion(@PathVariable Long alimentoId) {
        return existenciaService.sugerirUbicacion(alimentoId);
    }
}
