// 8/Febrero/2019
// Villalobos Valenzuela Jesús Héctor
package colas_y_pilas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Colas_y_pilas {

    public static void main(String[] args) {
        System.out.println("COLA");
        System.out.println("-------------");
        Queue<String> q = AgregaDatosQueue(args);
        ArrayList<String> lista = RutaQueue(q);
        
        Despliega(lista);
        System.out.println("-------------");
        System.out.println("PILA");
        System.out.println("-------------");
        
        Stack<String> s = AgregaDatosStack(lista);
        ArrayList<String> lista_regreso = RutaStack(s);
        Despliega(lista_regreso);

    }

    public static Queue<String> AgregaDatosQueue(String[] lista) {
        LinkedList<String> linked = new LinkedList<String>();
        Queue<String> cola = linked;
        
        for (int i = 0; i < lista.length; i++) {
            cola.add(lista[i]);
        }
        return cola;
    }

    public static ArrayList<String> RutaQueue(Queue<String> cola) {
        ArrayList<String> l = new ArrayList<>();
        while (!cola.isEmpty()){
            l.add(cola.remove());
        }
        
        return l;
    }

    public static void Despliega(ArrayList<String> lista) {
        for(String elemento : lista){
            System.out.println(elemento);
        }
    }

    public static Stack<String> AgregaDatosStack(ArrayList<String> lista) {
        
        Stack<String> pila = new Stack<>();
        for (int i = 0; i < lista.size(); i++) {
            pila.add(lista.get(i));  
        }
        return pila;
    }
    
    public static ArrayList<String> RutaStack(Stack<String> pila) {
        ArrayList<String> lista = new ArrayList<>();
        
        while (!pila.isEmpty()){
            lista.add(pila.pop());
        }
        
        return lista;
    }

}
