package grafo_arrays;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Grafo_arrays {

    static class Vertice {

        String nombre;
        boolean visitado;
        int indice;

        public Vertice(String nombre, Integer indice) {
            this.nombre = nombre;
            this.visitado = false;
            this.indice = indice;
        }

        public void Visitado() {
            visitado = true;
        }
    }

    static class Grafo {

        HashMap<String, Vertice> Vertices;
        int[][] Conexiones;
        int contador_vertices;

        public Grafo() {
            this.Vertices = new HashMap<>();
            this.Conexiones = new int[50][50];
            this.contador_vertices = 0;

        }

        public void DespliegaGrafo() {
            for (HashMap.Entry<String, Vertice> etiqueta : this.Vertices.entrySet()) {
                System.out.println(etiqueta.getValue().indice + " - " + etiqueta.getKey());
            }
            System.out.print(" ");
            for (int i = 0; i < this.contador_vertices; i++) {
                System.out.print(i + " ");
            }
            System.out.println("");
            System.out.println("----------------");
            for (int i = 0; i < this.contador_vertices; i++) {
                System.out.print(i + "|");
                for (int j = 0; j < this.contador_vertices; j++) {
                    System.out.print(this.Conexiones[i][j] + " ");
                }
                System.out.println("");
            }
        }

    }

    public static void main(String[] args) throws IOException {
        

        String origen = "jesus", destino = "karen", cadena, path;
        path = "/Users/villalobos28/OneDrive - Universidad de Sonora/Fuandamentos 3/grafo_con_arreglos.txt";
        Grafo g = CargaArchivos("/Users/villalobos28/OneDrive - Universidad de Sonora/Fuandamentos 3/lista.txt");
        //g.DespliegaGrafo();

        //cadena = EscribeGrafo(g);
        //graba_grafo(path,cadena);
        //DFS(g, origen, destino);
        BFS(g, origen, destino);

    }
    
    public static void BFS (Grafo grafo, String origen, String destino){
        boolean encontrado = false;
        Queue <Vertice> queue = new LinkedList <>();
        Vertice v = grafo.Vertices.get(origen);
        queue.add(v);
        do {
            v = queue.remove();
            if (v.nombre.equals(destino)) {
                encontrado = true;
                break;
            } else {
                v.visitado = true;
                add_adjacent(grafo,queue,v);
            }            
            System.out.println("Vert: " + v.nombre);
            despliega_queue(queue);
            
        } while (queue.isEmpty() == false && encontrado == false);
        if (encontrado == true) {
            System.out.println("Si se encontro un camino entre " + origen + " y " + destino);
        } else {
            System.out.println("No se encontro un camino entre " + origen + " y " + destino);
        }
        
    }
    
    public static void add_adjacent(Grafo grafo, Queue<Vertice> queue, Vertice v){
        int row = v.indice , col = 0;
        for (Map.Entry<String, Vertice> vertice : grafo.Vertices.entrySet()) {
            col = vertice.getValue().indice;
            if (grafo.Conexiones[row][col] > 0) {
                if (vertice.getValue().visitado == false) {
                    queue.add(vertice.getValue());
                }
            }
        }
    }
    
    public static void despliega_queue(Queue<Vertice> queue){
        System.out.println("Queue: ");
        for (Vertice vertice : queue) {
            System.out.print(vertice.nombre + " ");
        }
        System.out.println("\n");
    }

    public static void DFS(Grafo grafo, String origen, String destino) {

        Stack<Vertice> stack = new Stack<>();
        boolean encontrado = false;
        Vertice v = grafo.Vertices.get(origen);

        stack.push(v);
        do {
            v = stack.pop();
            if (v.nombre.equals(destino)) {
                encontrado = true;
                break;
            } else {
                v.visitado = true;
                push_adjacent(grafo, stack, v);
            }
            System.out.println("Vert: " + v.nombre);
            despliega_stack(stack);

        } while (stack.empty() == false && encontrado == false);
        if (encontrado == true) {
            System.out.println("Si se encontro un camino entre " + origen + " y " + destino);
        } else {
            System.out.println("No se encontro un camino entre " + origen + " y " + destino);
        }
    }

    public static void push_adjacent(Grafo grafo, Stack<Vertice> stack, Vertice v) {
        int row = 0, col = 0;
        row = v.indice;
        for (Map.Entry<String, Vertice> vertice : grafo.Vertices.entrySet()) {
            col = vertice.getValue().indice;
            if (grafo.Conexiones[row][col] > 0) {
                if (vertice.getValue().visitado == false) {
                    stack.push(vertice.getValue());
                }
            }
        }
    }

    public static void despliega_stack(Stack<Vertice> stack) {
        for (int i = 0; i < stack.size(); i++) {
            System.out.println("Stack: " + stack.get(i).nombre);
        }
        System.out.println();
    }

    public static Grafo CargaArchivos(String archivo) {
        // Creamo grafo
        Grafo grafo = new Grafo();

        try {
            //Leemos archivo
            try (Scanner sc = new Scanner(new File(archivo))) {
                //Leemos archivo
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (line.isEmpty()) {
                    } else {
                        String[] partes = line.split(",");
                        String origen = partes[0];
                        String destino = partes[1];
                        Integer peso = Integer.parseInt(partes[2]);
                        Integer ori = ObtenIndice(grafo, origen);
                        Integer des = ObtenIndice(grafo, destino);
                        grafo.Conexiones[ori][des] = peso;
                    }
                }
            }

        } catch (FileNotFoundException | NumberFormatException e) {
            System.err.println(e.getMessage());
        }
        return grafo;

    }

    public static Integer ObtenIndice(Grafo g, String etiqueta) {
        Integer indice = 0;
        if (g.Vertices.containsKey(etiqueta) == false) {
            indice = g.contador_vertices++;
            Vertice v = new Vertice(etiqueta, indice);
            g.Vertices.put(etiqueta, v);
        } else {
            Vertice v = new Vertice(etiqueta, g.Vertices.get(etiqueta).indice);
        }
        return indice;
    }

    public static String EscribeGrafo(Grafo g) {

        String cadena = "digraph grafo { ";
        String linea = "";
        String c = "";
        int con;

        for (HashMap.Entry<String, Vertice> vertice : g.Vertices.entrySet()) {
            c = vertice.getKey();
            for (HashMap.Entry<String, Vertice> vertice_destino : g.Vertices.entrySet()) {
                if (vertice.getValue() != vertice_destino.getValue()) {
                    cadena = cadena + vertice_destino.getKey();
                    //c = vertice.getKey();
                    cadena = cadena + "->" + c + "[weight = \"" + vertice_destino.getValue().indice + "\" label = \"" + vertice_destino.getValue().indice + "\"] \n";
                }
            }

        }
        cadena = cadena + "}";
        return cadena;

    }

    public static void graba_grafo(String path, String cadena) throws IOException {
        File archivo = new File(path);
        BufferedWriter bw;
        if (archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            System.out.println("El archivo ya existe en: " + path);
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(cadena);
            System.out.println("Se creo el archivo en: " + path);
        }
        bw.close();
    }
}
