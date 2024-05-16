package com.alurachallenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fecha_nacimiento;
    private Integer fecha_deceso;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libro;

    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();
        this.fecha_nacimiento = datosAutor.fecha_nacimiento();
        this.fecha_deceso = datosAutor.fecha_deceso();
    }

    @Override
    public String toString() {
        return String.format("Nombre: %s%nLibros: %s%nFecha de Nacimiento: %s%nFecha de Deceso: %s",nombre,libro,fecha_nacimiento,fecha_deceso);
    }

    public List<Libro> getLibro() {
        return libro;
    }

    public void setLibro(List<Libro> libro) {
        libro.forEach(e ->e.setAutor((this)));
        this.libro = libro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
