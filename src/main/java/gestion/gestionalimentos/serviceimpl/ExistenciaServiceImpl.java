package gestion.gestionalimentos.serviceimpl;

import gestion.gestionalimentos.entity.Alimento;
import gestion.gestionalimentos.entity.Existencia;
import gestion.gestionalimentos.entity.Ubicacion;
import gestion.gestionalimentos.repository.AlimentoRepository;
import gestion.gestionalimentos.repository.ExistenciaRepository;
import gestion.gestionalimentos.repository.UbicacionRepository;
import gestion.gestionalimentos.service.ExistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExistenciaServiceImpl implements ExistenciaService {

    @Autowired
    private ExistenciaRepository existenciaRepository;
    @Autowired
    private AlimentoRepository alimentoRepository;
    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Override
    public List<Existencia> getAllExistencias() {
        return existenciaRepository.findAll();
    }

    @Override
    public Optional<Existencia> getExistenciaById(Long id) {
        return existenciaRepository.findById(id);
    }

    @Override
    public Existencia saveExistencia(Existencia existencia) {
        return existenciaRepository.save(existencia);
    }

    @Override
    public Existencia updateExistencia(Long id, Existencia existencia) {
        Existencia existenciaExistente = existenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Existencia no encontrada"));

        existenciaExistente.setCantidad(existencia.getCantidad());
        existenciaExistente.setFechaEntrada(existencia.getFechaEntrada());

        if (existencia.getAlimento() != null && existencia.getAlimento().getId() != null) {
            Alimento alimento = alimentoRepository.findById(existencia.getAlimento().getId())
                    .orElseThrow(() -> new RuntimeException("Alimento no encontrado"));
            existenciaExistente.setAlimento(alimento);
        }

        if (existencia.getUbicacion() != null && existencia.getUbicacion().getId() != null) {
            Ubicacion ubicacion = ubicacionRepository.findById(existencia.getUbicacion().getId())
                    .orElseThrow(() -> new RuntimeException("Ubicacion no encontrada"));
            existenciaExistente.setUbicacion(ubicacion);
        }

        return existenciaRepository.save(existenciaExistente);
    }

    @Override
    public Existencia moveAlimento(Long existenciaId, Long newUbicacionId) {
        // Buscar la existencia
        Existencia existencia = existenciaRepository.findById(existenciaId)
                .orElseThrow(() -> new RuntimeException("Existencia no encontrada"));

        // Verificar que la nueva ubicación existe
        Ubicacion nuevaUbicacion = ubicacionRepository.findById(newUbicacionId)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));

        // Actualizar la ubicación
        existencia.setUbicacion(nuevaUbicacion);

        // Guardar cambios
        return existenciaRepository.save(existencia);
    }

    @Override
    public void deleteExistencia(Long id) {
        existenciaRepository.deleteById(id);
    }

    @Override
    public List<Existencia> getAlimentosProximosACaducar(int dias) {
        LocalDate hoy = LocalDate.now();
        LocalDate limite = hoy.plusDays(dias);

        // Buscar alimentos cuya fecha de caducidad está dentro del rango
        return existenciaRepository.findByAlimento_FechaCaducidadBetween(hoy, limite);
    }
    @Override
    public List<Existencia> getExistenciasOrdenadasPorFecha(Long ubicacionId) {
        return existenciaRepository.findByUbicacionIdOrderByFechaEntradaAsc(ubicacionId);
    }
    @Override
    public Existencia consumirAlimentoFIFO(Long ubicacionId, int cantidadConsumir) {
        List<Existencia> existencias = getExistenciasOrdenadasPorFecha(ubicacionId);

        // Asegúrate de manejar la lógica para dividir cantidades entre múltiples existencias
        Existencia primeraExistencia = existencias.get(0);
        if (primeraExistencia.getCantidad() >= cantidadConsumir) {
            primeraExistencia.setCantidad(primeraExistencia.getCantidad() - cantidadConsumir);
            return existenciaRepository.save(primeraExistencia);
        } else {
            throw new RuntimeException("No hay suficientes existencias para consumir");
        }
    }

    @Override
    public Ubicacion sugerirUbicacion(Long alimentoId) {
        // Obtener ubicaciones más usadas para este alimento
        List<Object[]> resultados = existenciaRepository.findUbicacionesFrecuentesPorAlimento(alimentoId);

        if (!resultados.isEmpty()) {
            Long ubicacionId = (Long) resultados.get(0)[0]; // La ubicación más frecuente
            return ubicacionRepository.findById(ubicacionId)
                    .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));
        } else {
            throw new RuntimeException("No hay datos suficientes para sugerir una ubicación");
        }
    }



}
