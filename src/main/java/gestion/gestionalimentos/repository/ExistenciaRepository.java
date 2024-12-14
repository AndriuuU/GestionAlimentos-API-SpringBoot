package gestion.gestionalimentos.repository;

import gestion.gestionalimentos.entity.Alimento;
import gestion.gestionalimentos.entity.Existencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExistenciaRepository extends JpaRepository<Existencia, Long> {
    void deleteByAlimento(Alimento alimento);
    //List<Existencia> findByFechaCaducidadBetween(LocalDate startDate, LocalDate endDate);
    List<Existencia> findByUbicacionIdOrderByFechaEntradaAsc(Long ubicacionId);
    List<Existencia> findByAlimento_FechaCaducidadBetween(LocalDate startDate, LocalDate endDate);
    @Query("SELECT e.ubicacion.id, COUNT(e.ubicacion.id) as frecuencia " +
            "FROM Existencia e WHERE e.alimento.id = :alimentoId " +
            "GROUP BY e.ubicacion.id ORDER BY frecuencia DESC")
    List<Object[]> findUbicacionesFrecuentesPorAlimento(@Param("alimentoId") Long alimentoId);

}
