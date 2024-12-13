package gestion.gestionalimentos.serviceimpl;

import gestion.gestionalimentos.entity.Ubicacion;
import gestion.gestionalimentos.repository.UbicacionRepository;
import gestion.gestionalimentos.service.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UbicacionServiceImpl implements UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    @Override
    public List<Ubicacion> getAllUbicaciones() {
        return ubicacionRepository.findAll();
    }

    @Override
    public Optional<Ubicacion> getUbicacionById(Long id) {
        return ubicacionRepository.findById(id);
    }

    @Override
    public Ubicacion saveUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    @Override
    public void deleteUbicacion(Long id) {
        ubicacionRepository.deleteById(id);
    }
}