package com.alurachallenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

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
        StringBuilder librosStr = new StringBuilder();
        librosStr.append("Libros: ");
        //librosStr.append("\n");
        /*for (Libro unlibro : libro) {
            librosStr.append(unlibro.getTitulo());
            librosStr.append(", ");
        }*/

        //Separar cada libro por una coma
        for (int i = 0; i < libro.size() ; i++) {
            librosStr.append(libro.get(i).getTitulo());
            if (i < libro.size() - 1 ){
                librosStr.append(", ");
            }
        }
        return String.format("---------- Autor ----------%nNombre: %s%n%s%nFecha de Nacimiento: %s%nFecha de Deceso: %s%n---------------------------%n",nombre,librosStr.toString(),fecha_nacimiento,fecha_deceso);
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
