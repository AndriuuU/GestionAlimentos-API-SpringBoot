package gestion.gestionalimentos.serviceimpl;

import gestion.gestionalimentos.entity.Alimento;
import gestion.gestionalimentos.repository.AlimentoRepository;
import gestion.gestionalimentos.repository.ExistenciaRepository;
import gestion.gestionalimentos.service.AlimentoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlimentoServiceImpl implements AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Autowired
    private ExistenciaRepository existenciaRepository;

    @Override
    public List<Alimento> getAllAlimentos() {
        return alimentoRepository.findAll();
    }

    @Override
    public Optional<Alimento> getAlimentoById(Long id) {
        return alimentoRepository.findById(id);
    }

    @Override
    public Alimento saveAlimento(Alimento alimento) {
       return crearAlimento(alimento.getNombre(), alimento.getTipo(), alimento.getEstado(),alimento.getFechaCaducidad());
    }

    public Alimento crearAlimento(String nombre, String tipo, String estado, LocalDate fechaCaducidad) {
        if (fechaCaducidad == null) {
            throw new IllegalArgumentException("La fecha de caducidad no puede ser nula");
        }

        Alimento alimento = new Alimento(nombre, tipo, estado, fechaCaducidad);
        return alimentoRepository.save(alimento);
    }


    @Override
    @Transactional
    public void deleteAlimento(Long id) {
        // Buscar el alimento
        Alimento alimento = alimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alimento no encontrado con ID: " + id));

        // Eliminar manualmente las existencias asociadas
        existenciaRepository.deleteByAlimento(alimento);

        // Finalmente, eliminar el alimento
        alimentoRepository.delete(alimento);
    }


    @Override
    public Alimento updateAlimento(Long id, Alimento newAlimento) {
        Alimento existingAlimento = alimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alimento no encontrado"));

        // Actualiza solo los campos proporcionados
        if (newAlimento.getNombre() != null) {
            existingAlimento.setNombre(newAlimento.getNombre());
        }
        if (newAlimento.getTipo() != null) {
            existingAlimento.setTipo(newAlimento.getTipo());
        }
        if (newAlimento.getEstado() != null) {
            existingAlimento.setEstado(newAlimento.getEstado());
        }
        if (newAlimento.getFechaCaducidad() != null) {
            existingAlimento.setFechaCaducidad(newAlimento.getFechaCaducidad());
        }

        return alimentoRepository.save(existingAlimento);
    }

}