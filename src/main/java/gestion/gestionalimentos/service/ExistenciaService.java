package gestion.gestionalimentos.service;

import gestion.gestionalimentos.entity.Existencia;
import gestion.gestionalimentos.entity.Ubicacion;

import java.util.List;
import java.util.Optional;

public interface ExistenciaService {
    List<Existencia> getAllExistencias();
    Optional<Existencia> getExistenciaById(Long id);
    Existencia saveExistencia(Existencia existencia);
    void deleteExistencia(Long id);

    Existencia updateExistencia(Long id, Existencia existencia);
    Existencia moveAlimento(Long existenciaId, Long newUbicacionId);
    List<Existencia> getAlimentosProximosACaducar(int dias);
    List<Existencia> getExistenciasOrdenadasPorFecha(Long ubicacionId);
    Existencia consumirAlimentoFIFO(Long ubicacionId, int cantidadConsumir);
    Ubicacion sugerirUbicacion(Long alimentoId);

}
