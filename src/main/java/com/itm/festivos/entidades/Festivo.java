package com.itm.festivos.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "festivo")
public class Festivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer dia;

    private Integer mes;

    private Integer diasPascua;

    @ManyToOne
    @JoinColumn(name = "id_pais")
    private pais pais;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private TipoFestivo tipo;

    public Festivo() {}

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getDia() {
        return dia;
    }

    public Integer getMes() {
        return mes;
    }

    public Integer getDiasPascua() {
        return diasPascua;
    }

    public pais getPais() {
        return pais;
    }

    public TipoFestivo getTipo() {
        return tipo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public void setDiasPascua(Integer diasPascua) {
        this.diasPascua = diasPascua;
    }

    public void setpais(pais pais) {
        this.pais = pais;
    }

    public void setTipo(TipoFestivo tipo) {
        this.tipo = tipo;
    }
}