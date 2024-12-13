package gestion.gestionalimentos.serviceimpl;

import gestion.gestionalimentos.entity.Existencia;
import gestion.gestionalimentos.repository.ExistenciaRepository;
import gestion.gestionalimentos.service.ExistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExistenciaServiceImpl implements ExistenciaService {

    @Autowired
    private ExistenciaRepository existenciaRepository;

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
    public void deleteExistencia(Long id) {
        existenciaRepository.deleteById(id);
    }
}
