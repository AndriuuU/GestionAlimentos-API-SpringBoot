package gestion.gestionalimentos.service;

import gestion.gestionalimentos.entity.Alimento;
import java.util.List;
import java.util.Optional;

public interface AlimentoService {
    List<Alimento> getAllAlimentos();
    Optional<Alimento> getAlimentoById(Long id);
    Alimento saveAlimento(Alimento alimento);
    void deleteAlimento(Long id);

}
