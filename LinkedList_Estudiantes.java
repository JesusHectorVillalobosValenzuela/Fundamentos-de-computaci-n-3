// 5/febrero/2019
// Villalobos Valenzuela Jesus Hector
package linkedlist_estudiantes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
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

public class LinkedList_Estudiantes {

    public static void main(String[] args) throws IOException {

        //LinkedList<Estudiante> alumnos = CreaListaAlumnos(args);
        LinkedList<Estudiante> alumnos = LeeNombres(args[0]);
        EliminaEstudiante(alumnos, args[1]);
        //DespliegaLinkedList(alumnos);
    }

    public static LinkedList<Estudiante> CreaListaAlumnos(String[] nombre) {
        LinkedList<Estudiante> alumnos = new LinkedList<>();

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

    public static void DespliegaLinkedList(LinkedList<Estudiante> estudiantes) {
        for (int i = 0; i < estudiantes.size(); i++) {
            System.out.println("Nombre: " + estudiantes.get(i).nombre);
            System.out.println("Edad: " + estudiantes.get(i).edad);
            System.out.println("Calificacion: " + estudiantes.get(i).calificacion);
            System.out.println("--------");
        }
    }

    public static LinkedList<Estudiante> LeeNombres(String nombre) throws FileNotFoundException {
        LinkedList<Estudiante> lista = new LinkedList<>();
        FileReader fr = new FileReader(nombre);
        BufferedReader bf = new BufferedReader(fr);
        String cadena;
        try {
            while ((cadena = bf.readLine()) != null) {
                int cal = getRandom(50, 100);
                int edad = getRandom(19, 25);
                Estudiante t = new Estudiante(cadena, edad, cal);
                lista.add(t);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return lista;
    }

    public static void EliminaEstudiante(LinkedList<Estudiante> lista, String nombre) {
        boolean encontrado = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).nombre.contains(nombre)) {
                lista.remove(i);
                encontrado = true;
                break;
            }
        }
        if (encontrado == true) {
            System.out.println("Alumno " +nombre+ " eliminado");
        } else{
            System.out.println("El alumno " +nombre+ " no se ha encontrado");
        }
        DespliegaLinkedList(lista);
    }

}
