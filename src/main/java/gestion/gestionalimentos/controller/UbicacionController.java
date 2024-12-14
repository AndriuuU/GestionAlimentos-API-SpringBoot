package gestion.gestionalimentos.controller;

import gestion.gestionalimentos.entity.Existencia;
import gestion.gestionalimentos.entity.Ubicacion;
import gestion.gestionalimentos.service.ExistenciaService;
import gestion.gestionalimentos.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @Autowired
    private ExistenciaService existenciaService;

    @GetMapping
    public List<Ubicacion> getAllUbicaciones() {
        return ubicacionService.getAllUbicaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubicacion> getUbicacionById(@PathVariable Long id) {
        Optional<Ubicacion> ubicacion = ubicacionService.getUbicacionById(id);
        return ubicacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ubicacion createUbicacion(@RequestBody Ubicacion ubicacion) {
        return ubicacionService.saveUbicacion(ubicacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ubicacion> updateUbicacion(@PathVariable Long id, @RequestBody Ubicacion ubicacion) {
        if (ubicacionService.getUbicacionById(id).isPresent()) {
            ubicacion.setId(id);
            return ResponseEntity.ok(ubicacionService.saveUbicacion(ubicacion));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable Long id) {
        if (ubicacionService.getUbicacionById(id).isPresent()) {
            ubicacionService.deleteUbicacion(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/ubicacion/{ubicacionId}/fifo")
    public List<Existencia> getExistenciasFIFO(@PathVariable Long ubicacionId) {
        return existenciaService.getExistenciasOrdenadasPorFecha(ubicacionId);
    }
}
