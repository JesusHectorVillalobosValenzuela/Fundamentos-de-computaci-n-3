package hashmap_autores;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

public class Hashmap_autores {

    public static class Libro {

        String amazon_index;
        String cover;
        String cover_url;
        String titulo;
        String autor;
        String categoria_id;
        String categoria;

        // Constructores
        public Libro(String amazon_index, String cover, String cover_url, String titulo, String autor, String categoria_id, String categoria) {
            this.amazon_index = amazon_index;
            this.cover = cover;
            this.cover_url = cover_url;
            this.titulo = titulo;
            this.autor = autor;
            this.categoria_id = categoria_id;
            this.categoria = categoria;

        }

        public void DespliegaLibro() {
            String salida = String.format(" | %-10s | %-10s | %-50s |", this.autor, this.categoria, this.titulo);
            System.out.println(salida);
        }
    }

    public static void main(String[] args) throws IOException {
        
        String path = "/Users/villalobos28/OneDrive - Universidad de Sonora/Fuandamentos 3/Archivos/book32-listing.csv";
        String autor = "Asimov";

        List<Libro> libros = CargaListaLibros(path);

        HashMap<String, List<Libro>> biblioteca = Crea_biblioteca(libros);
        List<Libro> result = Busca_hashmap(biblioteca, autor);
        DespliegaLista(result);
        
    }

    public static List<Libro> Busca_hashmap(HashMap<String, List<Libro>> biblioteca, String autor) {
        List<Libro> lista = new LinkedList<>();
        if (biblioteca.containsKey(autor) == true) {
            lista = biblioteca.get(autor);
        } else {
            System.out.println("El autor " + autor + " no existe en la lista.");
        }
        return lista;
    }

    public static HashMap<String, List<Libro>> Crea_biblioteca(List<Libro> libros) {
        HashMap<String, List<Libro>> mapa = new HashMap<>();
        
        for (Libro l : libros) {
            if (mapa.containsKey(l.autor)) {
                mapa.get(l.autor).add(l);
            } else {
                List<Libro> lista = new LinkedList<>();
                lista.add(l);
                mapa.put(l.autor, lista);
            }

            try {
                
                String[] partes = l.autor.split(" ");
                
                for (String nombre : partes) {
                    
                    if (mapa.containsKey(nombre)) {
                        
                        mapa.get(nombre).add(l);
                    } else {
                        
                        List<Libro> lista_libro = new LinkedList<>();
                        lista_libro.add(l);
                        mapa.put(nombre, lista_libro);
                    }

                }
                
            } catch (Exception e){
                System.out.println(e);
            }
        }
        return mapa;
    }

    public static List<Libro> CargaListaLibros(String archivo) throws IOException {

        //Creamos lista de libros
        List<Libro> listalibros = new LinkedList<>();
        try (ICsvListReader listReader = new CsvListReader(new FileReader(archivo), CsvPreference.STANDARD_PREFERENCE) //Logger.getLogger(Libros.class.getName()., type)
                ) {

            final CellProcessor[] processor = getProcessors();

            List<Object> lista;
            while ((lista = listReader.read(processor)) != null) {

                String amazon_id = (String) lista.get(0);
                String cover = (String) lista.get(1);
                String cover_url = (String) lista.get(2);
                String titulo = (String) lista.get(3);
                String autor;
                if (lista.get(4) == null) {
                    autor = " ";
                } else {
                    autor = (String) lista.get(4);
                }
                String categoria_id = (String) lista.get(5);
                String categoria = (String) lista.get(6);
                Libro ejemplar = new Libro(amazon_id, cover, cover_url, titulo, autor, categoria_id, categoria);
                listalibros.add(ejemplar);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return listalibros;
    }

    private static CellProcessor[] getProcessors() {

        final CellProcessor[] processors = new CellProcessor[]{
            null, //amazon_index
            null, //cover_filename
            null, //cover_url
            null, //titulo
            null, //autor
            null, //id_categoria
            null, //categoria
        };
        return processors;
    }

    public static void DespliegaLista(List<Libro> lista) {
        
        for(Libro libro: lista){
            libro.DespliegaLibro();
        }
        System.out.println("Total de libros encontrados: " + lista.size());
    }

}
