package com.alurachallenge.literalura.model;

public enum Idioma {
    en("[en]"),
    es("[es]");

    private String idiomaGutendex;
    Idioma(String idiomaGutendex){
        this.idiomaGutendex = idiomaGutendex;
    }

    public static Idioma fromString(String text){
        for (Idioma idioma : Idioma.values()){
            if (idioma.idiomaGutendex.equalsIgnoreCase(text)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }
}
