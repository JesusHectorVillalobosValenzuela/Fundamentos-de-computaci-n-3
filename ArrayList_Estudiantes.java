// 1/febrero/2019
// Villalobos Valenzuela Jesus Hector

package arraylist_estudiantes;

import java.util.ArrayList;
import java.util.Random;

class Estudiante {

    String nombre;
    int edad;
    int calificacion;

    public Estudiante(String nombre, int edad, int calificacion) {
        this.nombre = nombre;
        this.edad = edad;
        this.calificacion = calificacion;
    }

}

public class ArrayList_Estudiantes {

    public static void main(String[] args) {
        
        ArrayList<Estudiante> alumnos = CreaListaAlumnos(args);
        DespliegaArrayList(alumnos);

    }

    public static ArrayList<Estudiante> CreaListaAlumnos(String[] nombre){
        ArrayList<Estudiante> alumnos = new ArrayList<>();
        
        for (int i = 0; i < nombre.length; i++) {
            int cal = getRandom(50, 100);
            int edad = getRandom(19, 25);
            Estudiante t = new Estudiante(nombre[i], edad, cal);
            alumnos.add(t);
        }
        return alumnos;
    }    
    
    public static Integer getRandom(int lim_inf, int lim_sup) {
        Random r = new Random();
        return r.nextInt(lim_sup - lim_inf) + lim_inf;
    }

    public static void DespliegaArrayList(ArrayList<Estudiante> estudiantes) {
        for (Estudiante estudiante : estudiantes) {
            System.out.println("Nombre :" + estudiante.nombre);
            System.out.println("Edad :" + estudiante.edad);
            System.out.println("Calificacion :" + estudiante.calificacion);
            System.out.println("------------");
        }
    }

}
