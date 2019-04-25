// Aplicar metodos de ordenacion par arreglos.
// 17-01-2019, Villalobos Valenzuela Jesús Héctor
package ordenar_arreglos;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Ordenar_Arreglos {


    public static void main(String[] args) {
      ArrayList<Integer> arr = NumerosAleatorios(1000);
      int[] arreglo = PasaArregloCirett(arr);
      Instant start = Instant.now();
      //ShellSort(arreglo);
      //QuickSort(arreglo, 0, arreglo.length-1);
      //InsertionSort(arreglo);
      mergesort(arreglo,0,arreglo.length-1);
      Instant end = Instant.now();
      System.out.println("Tiempo: " + Duration.between(start, end));
      //DespliegaArreglo(arreglo);
    }

    public static ArrayList<Integer> NumerosAleatorios(int max_size) {
       int limite = 1000000000;
       int contador = 0;
       HashSet<Integer> numerosaleatorios = new HashSet();
       int numero = 0;
       while (contador < max_size) {
           numero = getRandom(1, limite);
           if (numerosaleatorios.contains(numero) == false) {
               numerosaleatorios.add(numero);
               contador = contador + 1;
           }
       }
       ArrayList<Integer> lista = new ArrayList<Integer>(numerosaleatorios);
       return lista;
   }

   public static Integer getRandom(int lim_inf, int lim_sup) {
       Random r = new Random();
       return r.nextInt(lim_sup - lim_inf) + lim_inf;
   }

   public static int[] PasaArregloCirett(ArrayList<Integer> arr) {
        int[] array = arr.stream().mapToInt(i -> i).toArray();
        return array;
    }

    public static void BubbleSort(int arr[]) {
        int aux = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[i] < arr[j]) {
                    aux = arr[i];
                    arr[i] = arr[j];
                    arr[j] = aux;
                }

            }
        }

    }

    public static void ShellSort(int[] lista) {
        int size = lista.length;
        int j;
        for (int gap = size / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < size; i++) {
                int temp = lista[i];
                for (j = i; j >= gap && lista[j - gap] > temp; j -= gap) {
                    lista[j] = lista[j - gap];
                }
                lista[j] = temp;
            }
        }
    }

    public static void QuickSort(int[] lista, int low, int high) {
        if (low < high) {
            //Particion index
            int pi = Partition(lista, low, high);
            QuickSort(lista, low, pi - 1);
            QuickSort(lista, pi + 1, high);
        }
    }

    public static int Partition(int[] lista, int low, int high) {
        int pivot = lista[high];
        int i = (low - 1); // Index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (lista[j] <= pivot) {
                i++;
                //Swap arr[i] and arr[-j]
                int temp = lista[i];
                lista[i] = lista[j];
                lista[j] = temp;
            }
        }
        // Swap arr[i+1] and arr[high] (or pivot)
        int temp = lista[i + 1];
        lista[i + 1] = lista[high];
        lista[high] = temp;

        return i + 1;
    }

    public static void InsertionSort(int[] array) {
        int j, key;
        for (int i = 1; i < array.length; i++) {
            key = array[i];
            j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public static void mergesort(int[] arreglo, int izq, int der) {
        if (izq < der) {
        // Encuentra la mitad
            int mitad = (izq + der) / 2;
        // Sort first and second halves
            mergesort(arreglo, izq, mitad);
            mergesort(arreglo, mitad + 1, der);
        // Merge the sorted halves
            merge(arreglo, izq, mitad, der);
        }
    }

    public static void merge(int[] arreglo, int left, int middle, int right) {
        // encuentra los tamaños de los arreglos a fusionar
        int n1 = middle - left + 1;
        int n2 = right - middle;
        // Crea arreglos temporales
        int L[] = new int[n1];
        int R[] = new int[n2];
        /*Copia datos a los arreglos temporales*/
        for (int i = 0; i < n1; ++i) {
            L[i] = arreglo[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arreglo[middle + 1 + j];
        }
        /* Mezcla los arreglos temporales */
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arreglo[k] = L[i];
                i++;
            } else {
                arreglo[k] = R[j];
                j++;
            }
            k++;
        }
        // copia el remanente de L si queda algo
        while (i < n1) {
            arreglo[k] = L[i];
            i++;
            k++;
        }
        // copia el remanente de R si queda algo
        while (j < n2) {
            arreglo[k] = R[j];
            j++;
            k++;
        }
    }

}
