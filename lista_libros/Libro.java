
package lista_libros;


public class Libro {

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
