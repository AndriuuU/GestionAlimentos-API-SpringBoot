package gestion.gestionalimentos.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@Table(name = "existencias")
public class Existencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "alimento_id")
    private Alimento alimento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    @Column(nullable = false)
    private int cantidad;

    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;

}
