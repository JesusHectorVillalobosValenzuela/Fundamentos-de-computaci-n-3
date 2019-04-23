package lista_libros;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;


public class Lista_libros {

    public static void main(String[] args) throws IOException {

        String archivo_entrada = "/Users/villalobos28/OneDrive - Universidad de Sonora/Fuandamentos 3/Archivos/book32-listing.csv";
        String autor = "Jess K. Alberts";

        List<Libro> libros = CargaListaLibros(archivo_entrada);
        List<Libro> Lista_ordenada = OrdenaAutores(libros);

        Libro libro = BusquedaBinaria(Lista_ordenada, 0, Lista_ordenada.size(), autor);
        
        libro.DespliegaLibro();
        //DespliegaLista(libros);
        //DespliegaLista(Lista_ordenada);
        //BuscaAutor(libros, autor);
    }
    
    
    
    public static Libro BusquedaBinaria (List<Libro> lista, int min, int max, String autor_a_buscar){
        Libro encontrado = null;
        int mitad = (min + max)/2;
        if (lista.get(mitad).autor.equals(autor_a_buscar)) {
            encontrado = lista.get(mitad);   
        } else{
            if (lista.get(mitad).autor.compareTo(autor_a_buscar) > 0) {
                if (min <= mitad -1) {
                    encontrado = BusquedaBinaria(lista, min, mitad-1, autor_a_buscar);
                }
            } else{
                if (mitad+1 <= max) {
                    encontrado = BusquedaBinaria(lista, mitad+1, max, autor_a_buscar);
                }
            }
        }
        return encontrado;
    }

    public static List<Libro> OrdenaAutores(List<Libro> lista) {
        Collections.sort(lista, (o1, o2) -> o1.autor.compareTo(o2.autor));

        Collections.sort(lista, new Comparator<Libro>() {
               @Override
               public int compare(Libro o1, Libro o2){
                return o1.autor.compareTo(o2.autor);
            }
            });
            return lista;
    }
       
    

    public static void BuscaAutor(List<Libro> lista, String autor) {
        int contador = 0;
        for (Libro l : lista) {
            if (l.autor.equals(autor)) {
                contador++;
                //System.out.println(autor+ " --> " +l.titulo);
                l.DespliegaLibro();
            }
        }
        System.out.println("\nEl autor " + autor + " se econtro " + contador + " veces.");

    }

    public static void DespliegaLista(List<Libro> lista) {
        for (Libro l : lista) {
            l.DespliegaLibro();
        }
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

    public static List< Libro> CargaListaLibros(String archivo) throws IOException {

        //Creamos lista de libros
        List<Libro> listalibros = new LinkedList<>();
        ICsvListReader listReader = null;

        try {
            listReader = new CsvListReader(new FileReader(archivo), CsvPreference.STANDARD_PREFERENCE);

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
        } finally {
            if (listReader != null) {

                listReader.close(); //Logger.getLogger(Libros.class.getName()., type)
            }
        }
        return listalibros;
    }

}
