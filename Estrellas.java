// Villalobs Valenzuela Jesús Héctor
package estrellas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Estrellas {

    public static void main(String[] args) {

        System.out.println("cola");
        Queue<Integer> q = AgregaDatosQueue(args);
        ArrayList<Integer> lista = RutaQueue(q);
        DisplayEstrellas(lista);

        System.out.println("-      -");

        System.out.println("pila");
        Stack<Integer> s = AgregaDatosStack(lista);

        ArrayList<Integer> listaS = RutaStack(s);
        DisplayEstrellas(listaS);

    }

    public static Queue<Integer> AgregaDatosQueue(String[] lista) {
        LinkedList<Integer> arr = new LinkedList<>();
        Queue<Integer> cola = arr;

        for (int i = 0; i < lista.length; i++) {
            cola.add(Arrays.hashCode(lista));
        }
        return cola;
    }

    public static ArrayList<Integer> RutaQueue(Queue<Integer> cola) {
        ArrayList<Integer> l = new ArrayList<>();
        while (!cola.isEmpty()) {
            l.add(cola.remove());
        }

        return l;
    }

    public static Stack<Integer> AgregaDatosStack(ArrayList<Integer> lista) {
        Stack<Integer> pila = new Stack<>();
        for (int i = 0; i < lista.size(); i++) {
            pila.push(lista.get(i));
        }
        return pila;
    }

    public static ArrayList<Integer> RutaStack(Stack<Integer> pila) {
        ArrayList<Integer> lista = new ArrayList<>();

        while (!pila.isEmpty()) {
            lista.add(pila.pop());
        }

        return lista;
    }

    public static void DisplayEstrellas(ArrayList<Integer> lista) {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.get(i); j++) {
                System.out.println("-");
            }
            System.out.println("*");
        }
    }

}
