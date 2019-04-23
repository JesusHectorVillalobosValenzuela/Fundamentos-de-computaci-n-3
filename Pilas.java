
// 8/febrero/2019
// Villalobos Vanzuela Jesús Héctor

package pilas;

import java.util.Stack;

public class Pilas {


    public static void main(String[] args) {

        Stack<String> pila = AgregaDatos(args);
        DespliegaPila(pila);
    }

    public static Stack<String> AgregaDatos(String[] argss) {
        Stack<String> pila = new Stack<>();
        for (int i = 0; i < argss.length; i++) {
            pila.push(argss[i]);
        }
        return pila;
    }

    public static void DespliegaPila(Stack<String> pila) {
        while (!pila.isEmpty()) {
            System.out.println(pila.pop());
        }

    }

}
