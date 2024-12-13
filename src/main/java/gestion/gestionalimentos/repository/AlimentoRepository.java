package gestion.gestionalimentos.repository;

import gestion.gestionalimentos.entity.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentoRepository  extends JpaRepository<Alimento, Long> {
}
