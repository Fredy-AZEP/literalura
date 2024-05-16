package com.alurachallenge.literalura.principal;

import com.alurachallenge.literalura.model.*;
import com.alurachallenge.literalura.repository.AutorRepository;
import com.alurachallenge.literalura.repository.LibroRepository;
import com.alurachallenge.literalura.service.ConsumoAPI;
import com.alurachallenge.literalura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositoryLibro;
    private AutorRepository repositoryAutor;
    private List<Autor> autores;
    private List<Libro> libros;

    public Principal(LibroRepository repositoryLibro, AutorRepository repositoryAutor) {
        this.repositoryLibro = repositoryLibro;
        this.repositoryAutor = repositoryAutor;
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

        //String json = consumoAPI.obtenerDatos(URL_BASE + "romeo");
        //String json = consumoAPI.obtenerDatos(URL_BASE + "macbeth");
        //String json = consumoAPI.obtenerDatos(URL_BASE + "Shakespeare%27s%20Sonnets");
        String json = consumoAPI.obtenerDatos(URL_BASE + "adsdasd");
        //System.out.printf(json + "\n");
        DatosBusqueda datos = conversor.obtenerDatos(json, DatosBusqueda.class);
        return datos;

    }


    public void buscarLibro() {

        DatosBusqueda datosBusqueda = getBusqueda();
        if (datosBusqueda != null && !datosBusqueda.resultado().isEmpty()) {
            DatosLibro primerLibro = datosBusqueda.resultado().get(0);

            System.out.println(primerLibro);

            Libro libro = new Libro(primerLibro);

            //System.out.println("----- Libro -----");
            //System.out.println(libro);
            //System.out.println("-----------------");


            //repositoryLibro.save(libro);


            if (!primerLibro.autor().isEmpty()) {
                DatosAutor autor = primerLibro.autor().get(0);
                //System.out.println(autor);
                Autor autor1 = new Autor(autor);
                //var autorNombre = autor.nombre();
                //System.out.println("Autor nombre: " + autorNombre);
                //List<Autor> autor1 = Collections.singletonList(new Autor(autor));
                //System.out.println("----- Autor -----");
                //System.out.println(autor1);
                //System.out.println("-----------------");

                Optional<Autor> autorOptional = repositoryAutor.findByNombre(autor1.getNombre());
                if (autorOptional.isPresent()) {
                    Autor autorExiste = autorOptional.get();
                    libro.setAutor(autorExiste);
                    repositoryLibro.save(libro);

                } else {
                    Autor autorNuevo = repositoryAutor.save(autor1);
                    libro.setAutor(autorNuevo);
                    repositoryLibro.save(libro);


                }
                System.out.println("----- Libro -----");
                System.out.printf("Titulo: %s%nAutor: %s%nIdioma: %s%nNumero de Descargas: %d"
                        ,libro.getTitulo(),autor1.getNombre(),libro.getLenguaje(),libro.getNumero_descargas());
                System.out.println("-----------------\n");

            } else {
                System.out.println("Sin autor");
            }

        } else {
            System.out.println("Libro no encontrado");
        }
    }




}
