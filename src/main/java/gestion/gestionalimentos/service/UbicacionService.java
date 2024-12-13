package gestion.gestionalimentos.service;

import gestion.gestionalimentos.entity.Ubicacion;

import java.util.List;
import java.util.Optional;

public interface UbicacionService {
    List<Ubicacion> getAllUbicaciones();
    Optional<Ubicacion> getUbicacionById(Long id);
    Ubicacion saveUbicacion(Ubicacion ubicacion);
    void deleteUbicacion(Long id);
}
