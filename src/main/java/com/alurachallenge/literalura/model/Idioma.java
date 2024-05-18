package com.alurachallenge.literalura.model;

public enum Idioma {
    en("[en]", "Ingles"),
    es("[es]", "Español");

    private String idiomaGutendex;
    private String idiomaEspanol;
    Idioma(String idiomaGutendex, String idiomaEspanol){
        this.idiomaGutendex = idiomaGutendex;
        this.idiomaEspanol = idiomaEspanol;
    }

    public static Idioma fromString(String text){
        for (Idioma idioma : Idioma.values()){
            if (idioma.idiomaGutendex.equalsIgnoreCase(text)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }

    public static Idioma fromEspanol(String text){
        for (Idioma idioma : Idioma.values()){
            if (idioma.idiomaEspanol.equalsIgnoreCase(text)){
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun idioma encontrado: " + text);
    }
}
