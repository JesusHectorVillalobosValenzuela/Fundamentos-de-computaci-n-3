package libros;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author villalobos28
 */
class Libro {

    String amazon_index;
    String cover_filename;
    String cover_url;
    String titulo;
    String autor;
    String id_categoria;
    String categoria;
    
    String isbn;
    int anio;

    public Libro(String amazon_index, String cover_filename, String cover_url, String titulo, String autor, String id_categoria, String categoria) {
        this.amazon_index = amazon_index;
        this.cover_filename = cover_filename;
        this.cover_url = cover_url;
        this.titulo = titulo;
        this.autor = autor;
        this.id_categoria = id_categoria;
        this.categoria = categoria;
    }

    Libro(String isbn, String titulo, String autor, int anio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    public void DespliegaLibro() {
        String salida = String.format(" | %-20s | %-15s | %-80s |", this.autor, this.categoria, this.titulo);
        //System.out.printf(" | %20s | %15s | %50s |", this.autor, this.categoria, this.titulo);
        System.out.println(salida);
    }

}

public class Libros {

    public static void main(String[] args) {
        // TODO code application logic here
        //archivo a leer
        //String archivo_entrada = args[0];
        String archivo_entrada = "/Users/villalobos28/Downloads/book32-listing.csv";
        String autor = "Stephen King";

        ArrayList<Libro> libros = CargaListaLibros(archivo_entrada);

        //Mostrar libros
        DespliegaLista(libros);

        //Busca autor
        BuscaAutor(libros, autor);
    }

    private static void BuscaAutor(ArrayList<Libro> libros, String autor) {
        for (Libro lista : libros) {
            if (lista.autor.equals(autor)) {
                System.out.println("Autor " + autor + " encontrado.");
            } else {
                System.out.println("El autor " + autor + " no se encuentra en la lista.");
            }
        }
    }

    private static void DespliegaLista(ArrayList<Libro> libros) {
        ArrayList<Libro> lista = new ArrayList<>();
        for (Libro libro : libros) {
            try {
                System.out.println("Año " + libro.anio);
                System.out.println("Autor " + libro.autor);
                System.out.println("Isbn " + libro.isbn);
                System.out.println("Titulo " + libro.titulo);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }

    public static ArrayList<Libro> CargaListaLibros(String archivo) {
        //Creamos lista de Libros
        ArrayList<Libro> lista = new ArrayList<Libro>();

        try {
            //Abrimos CSV
            Scanner scanner = new Scanner(new File(archivo));
            scanner.useDelimiter(",");

            //Leemos archivo
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                String[] partes = line.split(",");

                String isbn = partes[0];
                String titulo = partes[1];
                String autor = partes[2];
                int anio = Integer.parseInt(partes[3]);

                Libro ejemplar;
                ejemplar = new Libro(isbn, titulo, autor, anio);
                lista.add(ejemplar);
            }

            scanner.close();
        } catch (FileNotFoundException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }

}
