package gestion.gestionalimentos.service;

import gestion.gestionalimentos.entity.Existencia;

import java.util.List;
import java.util.Optional;

public interface ExistenciaService {
    List<Existencia> getAllExistencias();
    Optional<Existencia> getExistenciaById(Long id);
    Existencia saveExistencia(Existencia existencia);
    void deleteExistencia(Long id);

    Existencia updateExistencia(Long id, Existencia existencia);
}
