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
                    scan.nextLine(); // limpiar buffer antes de mostrar
                    AG.mostrarArbol(AG.getCabeza(), "");
                    System.out.println("Presione Enter para continuar...");
                    scan.nextLine();
                    break;
                case 2:
                    scan.nextLine(); // limpiar buffer antes de leer cadenas
                    System.out.print("Ingrese el nombre: ");
                    String nombre = scan.nextLine();

                    System.out.print("Ingrese la cédula: ");
                    String cedula = scan.nextLine();

                    System.out.print("Ingrese la edad: ");
                    int edad = scan.nextInt();
                    scan.nextLine(); // limpiar buffer

                    if (AG.getCabeza() == null) {
                        AG.insertarPersona(nombre, cedula, edad, null);
                    } else {
                        System.out.print("Ingrese la cédula del padre: ");
                        String cedulaPadre = scan.nextLine();
                        AG.insertarPersona(nombre, cedula, edad, cedulaPadre);
                    }
                    System.out.println("Presione Enter para continuar...");
                    scan.nextLine();
                    break;
                case 4:
                    System.out.print("Ingrese la cédula del hijo: ");
                    scan.nextLine(); // limpiar buffer
                    String cedulaHijo = scan.nextLine();
                    AG.mostrarPadre(cedulaHijo);
                    System.out.println("Presione Enter para continuar...");
                    scan.nextLine();
                    break;
                default:
                    System.out.println("Ingrese un valor valido");
                    break;
            }

        } while (aux);
    }
}
