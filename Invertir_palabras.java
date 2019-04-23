
package invertir_palabras;

    // Villalobos Valenzuela Jesús Héct
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



public class Invertir_palabras {

    public static void main(String[] args) {
        
        Queue<String> q = AgregaDatosQueue(args);
        ArrayList<String> array = ProcesaQueue(q);
        Despliega(array);
        
    }
    
    
    
    public static ArrayList<String> ProcesaQueue(Queue<String> cola){
        ArrayList<String> arreglo = new ArrayList<>();
        String cadena_original, cadena_inversa;  
        
        while (!cola.isEmpty()){
            cadena_original = cola.remove();
            cadena_inversa = invertir(cadena_original);
            arreglo.add(cadena_inversa);
            
        }
        
        return arreglo;
    }
    
    public static String invertir(String cadena){
        String inverso = null;
        char[] arreglo = cadena.toCharArray();
        Stack<Character> pila = new Stack<>();
        for (int i = 0; i < arreglo.length; i++) {
            pila.push(arreglo[i]);
        }
        int i = 0;
        while(!pila.isEmpty()){
            arreglo[i++] =  (char) pila.pop();
        }
        inverso = Arrays.toString(arreglo);
        return inverso;
    }
    
    public static Queue<String> AgregaDatosQueue(String[] lista) {
        LinkedList<String> linked = new LinkedList<String>();
        Queue<String> cola = linked;
        
        for (int i = 0; i < lista.length; i++) {
            cola.add(lista[i]);
        }
        return cola;
    }
    
    public static void Despliega(ArrayList<String> lista) {
        for(String elemento : lista){
            System.out.println(elemento);
        }
    }
    
    
}
