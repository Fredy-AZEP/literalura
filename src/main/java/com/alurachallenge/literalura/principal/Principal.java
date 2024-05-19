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


    public void muestrMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar Libros Por Titulo
                    2 - Mostrar Libros Registrados
                    3 - Mostrar Autores Registrados
                    4 - Autores Vivos En Determinado Año
                    5 - Buscar Libros Por Idioma
                    6 - Top 10 Libros Mas Descargados
                                        
                    0 - Salir
                    """;
            System.out.println(menu);
            while (!teclado.hasNextInt()) {
                System.out.println("Formato invalido, ingrese un numero que este disponible en el menu!");
                teclado.nextLine();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    mostrarLibros();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    autoresVivosPorAno();
                    break;
                case 5:
                    buscarLibroPorIdioma();
                    break;
                case 6:
                    top10Libros();
                    break;
                case 0:
                    System.out.println("Saliendo de la Aplicaccion");
                    break;
                default:
                    System.out.printf("Opcion Invalida\n");
            }
        }
    }


    private DatosBusqueda getBusqueda() {

        //String json = consumoAPI.obtenerDatos(URL_BASE + "romeo");
        //String json = consumoAPI.obtenerDatos(URL_BASE + "macbeth");
        //String json = consumoAPI.obtenerDatos(URL_BASE + "Shakespeare%27s%20Sonnets");
        //String json = consumoAPI.obtenerDatos(URL_BASE + "divine");
        //String json = consumoAPI.obtenerDatos(URL_BASE + "quijote");
        //String json = consumoAPI.obtenerDatos(URL_BASE + "dividfdsfsdfne");
        //System.out.printf(json + "\n");

        System.out.println("Escribe el nombre del libro: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "%20"));
        //System.out.println(json);
        DatosBusqueda datos = conversor.obtenerDatos(json, DatosBusqueda.class);
        return datos;

    }


    private void buscarLibro() {

        DatosBusqueda datosBusqueda = getBusqueda();
        if (datosBusqueda != null && !datosBusqueda.resultado().isEmpty()) {
            DatosLibro primerLibro = datosBusqueda.resultado().get(0);

            //System.out.println(primerLibro);

            Libro libro = new Libro(primerLibro);

            //System.out.println("----- Libro -----");
            //System.out.println(libro);
            //System.out.println("-----------------");


            //repositoryLibro.save(libro);
            Optional<Libro> libroExiste = repositoryLibro.findByTitulo(libro.getTitulo());
            if (libroExiste.isPresent()){
                System.out.println("\nEl libro ya esta registrado!\n");
            }else {


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
                    //String idioma = String.join(", ", libro.getLenguaje());
                    //idioma = idioma.replace("[","").replace("]","");
                    System.out.println("---------- Libro ----------");
                    System.out.printf("Titulo: %s%nAutor: %s%nIdioma: %s%nNumero de Descargas: %d%n"
                            , libro.getTitulo(), autor1.getNombre(), libro.getLenguaje(), libro.getNumero_descargas());
                    System.out.println("---------------------------\n");

                } else {
                    System.out.println("Sin autor");
                }
            }

        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private void mostrarLibros() {
        libros = repositoryLibro.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    private void mostrarAutores() {
        autores = repositoryAutor.findAll();
        autores.stream()
                .forEach(System.out::println);
    }

    private void autoresVivosPorAno() {
        System.out.println("Ingresa el año vivo de autor(es) que desea buscar: ");
        var ano = teclado.nextInt();
        autores = repositoryAutor.listaAutoresVivosPorAno(ano);
        autores.stream()
                .forEach(System.out::println);
    }

    private List<Libro> datosBusquedaLenguaje(String idioma) {
        //System.out.println("Escribe el nombre del lenguaje/idioma que desea buscar: ");
        //var lenguaje = teclado.nextLine();
        var dato = Idioma.fromString(idioma);
        System.out.println("Lenguaje buscado: " + dato);

        List<Libro> libroPorIdioma = repositoryLibro.findByLenguaje(dato);
        //libroPorIdioma.forEach(System.out::println);
        return libroPorIdioma;
    }

    private void buscarLibroPorIdioma() {
        //var lenguaje = "Español";
        //var idioma = Idioma.fromEspanol("Español");
        /*System.out.println("Escribe el nombre del lenguaje/idioma que desea buscar: ");
        var lenguaje = teclado.nextLine();
        var idioma = Idioma.fromEspanol(lenguaje);
        System.out.println("Lenguaje buscado: " + idioma);

        List<Libro> libroPorIdioma = repositoryLibro.findByLenguaje(idioma);

         */
        //libroPorIdioma.forEach(System.out::println);
        System.out.println("Selecciona el lenguaje/idioma que deseas buscar: ");

        var opcion = -1;
        while (opcion != 0) {
            var opciones = """
                    1. en - Ingles
                    2. es - Español
                                        
                    0. Volver A Las Opciones Anteriores
                    """;
            System.out.println(opciones);
            while (!teclado.hasNextInt()) {
                System.out.println("Formato invalido, ingrese un numero que este disponible en el menu!");
                teclado.nextLine();
            }
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    List<Libro> librosEnIngles = datosBusquedaLenguaje("[en]");
                    librosEnIngles.forEach(System.out::println);
                    break;
                case 2:
                    List<Libro> librosEnEspanol = datosBusquedaLenguaje("[es]");
                    librosEnEspanol.forEach(System.out::println);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Ningun idioma seleccionado");
            }
        }
    }

    private void top10Libros(){
        List<Libro> topLibros = repositoryLibro.top10LibrosMasDescargados();
        topLibros.forEach(System.out::println);
    }

}
