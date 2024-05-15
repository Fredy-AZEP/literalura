package com.alurachallenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Autor {
    private String nombre;
    private Integer fecha_nacimiento;
    private Integer fecha_deceso;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Integer fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Integer getFecha_deceso() {
        return fecha_deceso;
    }

    public void setFecha_deceso(Integer fecha_deceso) {
        this.fecha_deceso = fecha_deceso;
    }
}
