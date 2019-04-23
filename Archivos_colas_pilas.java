
package archivos_colas_pilas;

import java.io.File;
import java.util.Stack;

public class Archivos_colas_pilas {

    
    public static void main(String[] args) {
        // TODO code application logic here
        
        String path = "/Users/villalobos28";
        Stack<String> pila = new Stack<>();
        ObtenerFolders(pila,path);
        
    }
    
    public static void ObtenerFolders (Stack<String> pila, String directorio){

      try{
        File [] archivos = new File(directorio).listFiles();
        if (archivos != null) {
          for (File archivo : archivos  ) {
              if (archivo.isFile()) {
                pila.push(archivo.getName());
              }
              if (archivo.isDirectory()) {
                ObtenerFolders(pila,archivo.getPath());
                pila.push(">>>"+archivo.getName());
              }
          }

        }
      } catch(Exception e){
        System.out.print(e.getMessage());
      }
      DespliegaStack(pila);

    }

    public static void DespliegaStack(Stack<String> pila){
      while (!pila.isEmpty()) {
        System.out.println(pila.pop());
      }
    }
    
}
