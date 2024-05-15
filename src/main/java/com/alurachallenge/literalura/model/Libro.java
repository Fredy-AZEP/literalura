package com.alurachallenge.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @Transient
    private List<Autor> autor;
    private String lenguaje;
    private Integer numero_descargas;

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.lenguaje = datosLibro.lenguaje().toString();
        this.numero_descargas = datosLibro.numero_descargas();
    }
    @Override
    public String toString() {
        String idioma = String.join(", ", lenguaje);
        idioma = idioma.replace("[","").replace("]","");
        return String.format("Titulo: %s%nAutor: %s%nIdioma: %s%nNumero de Descargar: %d",titulo,autor,idioma,numero_descargas);
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getNumero_descargas() {
        return numero_descargas;
    }

    public void setNumero_descargas(Integer numero_descargas) {
        this.numero_descargas = numero_descargas;
    }
}
