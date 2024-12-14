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
    public void deleteExistencia(Long id) {
        existenciaRepository.deleteById(id);
    }
}
