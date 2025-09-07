package Listas_Generalizadas;

import java.util.Scanner;

public class Menu {
    ArbolGenealogico AG = new ArbolGenealogico();

    public Menu() {
        boolean aux = true;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println(" |-------------------- MENU ---------------------|" +
                    "\n |-----------------------------------------------|" +
                    "\n |   1. Mostrar todos los datos del árbol.       |" +
                    "\n |   2. Insertar una persona.                    |" +
                    "\n |   3. Actualizar                               |" +
                    "\n |   4. Mostrar padre.                           |" +
                    "\n |   5. Mostrar hijos.                           |" +
                    "\n |   6. Mostrar hermanos.                        |" +
                    "\n |   7. Mostrar ancestros.                       |" +
                    "\n |   8. Mostrar descendientes.                   |" +
                    "\n |   9. Mostrar nodo con mayor grado.            |" +
                    "\n |   10. Mostrar nodo con mayor nivel.           |" +
                    "\n |   11. Mostrar altura del árbol.               |" +
                    "\n |   12. Mostrar nivel de un registro.           |" +
                    "\n |   13. Mostrar registros de un nivel.          |" +
                    "\n |   14. Eliminar nodos de un nivel.             |" +
                    "\n |   0. Salir.                                   |" +
                    "\n |-----------------------------------------------|" +
                    "\n  Seleccione la opcion que desee: ");

            int op = scan.nextInt();

            switch (op) {
                case 0:
                    System.out.println("Hasta Luego");
                    aux = false;
                    scan.close();
                    break;
                case 1:
                    AG.mostrarArbol(AG.getCabeza(), "");
                    break;
                default:
                    System.out.println("Ingrese un valor valido");
                    break;
            }

        } while (aux);
    }
}
