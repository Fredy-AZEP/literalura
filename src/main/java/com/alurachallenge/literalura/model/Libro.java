package com.alurachallenge.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    //@Column(unique = true)
    private String titulo;
    @ManyToOne
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Idioma lenguaje;
    private Integer numero_descargas;

    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        //this.lenguaje = datosLibro.lenguaje().toString();
        this.lenguaje = Idioma.fromString(datosLibro.lenguaje().toString().split(",")[0].trim());
        this.numero_descargas = datosLibro.numero_descargas();
    }
    @Override
    public String toString() {
        //String idioma = String.join(", ", lenguaje);
        //idioma = idioma.replace("[","").replace("]","");
        String nombreAutor = autor.getNombre();
        return String.format("---------- Libro ----------%nTitulo: %s%nAutor: %s%nIdioma: %s%nNumero de Descargar: %d%n---------------------------%n",titulo,nombreAutor,lenguaje,numero_descargas);
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Idioma lenguaje) {
        this.lenguaje = lenguaje;
    }

    public Integer getNumero_descargas() {
        return numero_descargas;
    }

    public void setNumero_descargas(Integer numero_descargas) {
        this.numero_descargas = numero_descargas;
    }
}
