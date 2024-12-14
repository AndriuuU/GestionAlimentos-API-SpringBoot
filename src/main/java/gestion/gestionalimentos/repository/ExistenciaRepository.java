package gestion.gestionalimentos.repository;

import gestion.gestionalimentos.entity.Alimento;
import gestion.gestionalimentos.entity.Existencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExistenciaRepository extends JpaRepository<Existencia, Long> {
    void deleteByAlimento(Alimento alimento);

}
