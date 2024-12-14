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
@Table(name = "ubicacion")
public class Ubicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descripcion;

    @Column(name = "tipo_ubicacion", nullable = false)
    private String tipoUbicacion;

    @Column(nullable = false)
    private int capacidad;

    // Constructor sin parámetros
    public Ubicacion() {
    }

    // Constructor con parámetros
    public Ubicacion(Long id, String descripcion, String tipoUbicacion, int capacidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipoUbicacion = tipoUbicacion;
        this.capacidad = capacidad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoUbicacion() {
        return tipoUbicacion;
    }

    public void setTipoUbicacion(String tipoUbicacion) {
        this.tipoUbicacion = tipoUbicacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}