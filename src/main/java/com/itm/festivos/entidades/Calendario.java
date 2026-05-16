package com.itm.festivos.entidades;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "calendario")
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String descripcion;

    public Calendario() {
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}