package gestion.gestionalimentos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;



@Entity
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @NotNull(message = "El tipo no puede ser nulo")
    private String tipo;

    @Column
    private String estado;

    @OneToMany(mappedBy = "alimento", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Existencia> existencias;

    @NotNull(message = "La fecha de caducidad no puede ser nula")
    @Column(name = "fecha_caducidad", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCaducidad;

    // Constructor por defecto
    public Alimento() {}

    // Constructor con parámetros (todos los campos)
    public Alimento(String nombre, String tipo, String estado, LocalDate fechaCaducidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaCaducidad = fechaCaducidad;
    }

    // Getter y Setter para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter y Setter para nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para tipo
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Getter y Setter para estado
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
}

