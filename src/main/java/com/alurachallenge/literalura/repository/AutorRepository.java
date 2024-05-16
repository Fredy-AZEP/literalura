package com.alurachallenge.literalura.repository;

import com.alurachallenge.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository <Autor, Long> {
    Optional<Autor> findByNombre(String nombre);
}
