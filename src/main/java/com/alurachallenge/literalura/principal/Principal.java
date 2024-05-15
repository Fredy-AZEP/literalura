package com.alurachallenge.literalura.principal;

import com.alurachallenge.literalura.model.DatosAutor;
import com.alurachallenge.literalura.model.DatosBusqueda;
import com.alurachallenge.literalura.model.DatosLibro;
import com.alurachallenge.literalura.model.Libro;
import com.alurachallenge.literalura.repository.LibroRepository;
import com.alurachallenge.literalura.service.ConsumoAPI;
import com.alurachallenge.literalura.service.ConvierteDatos;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repository;
    public Principal(LibroRepository repository){
        this.repository = repository;
    }


    /*public void muestrMenu(){
        var opcion = -1;
        while (opcion != 0){
            var menu = """
                    1 - Buscar Libros
                    
                    0 - Salir
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarLibro();
                    break;
                case 0:
                    System.out.println("Saliendo de la Aplicaccion");
                    break;
                default:
                    System.out.printf("Opcion Invalida");
            }
        }
    }*/


    private DatosBusqueda getBusqueda() {

        String json = consumoAPI.obtenerDatos(URL_BASE + "romeo");
        System.out.printf(json+"\n");
        DatosBusqueda datos = conversor.obtenerDatos(json, DatosBusqueda.class);
        return datos;

    }


    public void buscarLibro(){

        DatosBusqueda datosBusqueda = getBusqueda();
        if (datosBusqueda != null && !datosBusqueda.resultado().isEmpty()){
            DatosLibro primerLibro = datosBusqueda.resultado().get(0);
            System.out.println(primerLibro);

            Libro libro = new Libro(primerLibro);
            System.out.println("----- Libro -----");
            System.out.println(libro);
            System.out.println("-----------------");

            //repository.save(libro);

            if (!primerLibro.autor().isEmpty()){
                DatosAutor autor = primerLibro.autor().get(0);
                System.out.printf("Titulo " + primerLibro.titulo() +
                        " Autor " + autor.nombre()+
                        " Idioma " + primerLibro.lenguaje()+
                        " Numero de Descargas " + primerLibro.numero_descargas()
                        +"\n");
            }
        } else {
            System.out.println("No se encontraron libros");
        }

    }

    /*public void test(){
        DatosBusqueda datos = getBusqueda();
        Libro libro = new Libro(datos);
    }*/
}
