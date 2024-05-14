package com.alurachallenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fecha_nacimiento,
        @JsonAlias("death_year") Integer fecha_deceso
) {
}
