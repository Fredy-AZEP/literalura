package com.alurachallenge.literalura.repository;

import com.alurachallenge.literalura.model.Idioma;
import com.alurachallenge.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByLenguaje(Idioma idioma);
}
