package Listas_Generalizadas;

import javax.swing.*;

public class ArbolGenealogico {

    // Atributos
    private Nodo cabeza;

    // Constructor
    public ArbolGenealogico() {
        this.cabeza = null;
    }

    public ArbolGenealogico(Nodo raiz) {
        this.cabeza = raiz;
    }

    // Getter y Setter
    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    // Busqueda por cedula
    public Nodo buscarPorCedula(Nodo nodo, String cedula) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getCedula().equals(cedula)) {
            return nodo;
        }
        // Buscar en los hijos
        Nodo resultado = buscarPorCedula(nodo.getLigalista(), cedula);
        if (resultado != null) {
            return resultado;
        }
        // Buscar en los hermanos
        return buscarPorCedula(nodo.getLiga(), cedula);
    }

    //Mostrar Arbol
    public void mostrarArbol(Nodo nodo, String prefijo) {
        //La variable prefijo se usa para la indentación.
         if (nodo == null) {
             // Solo mostrar mensaje si es la raíz y no hay árbol
             if (prefijo.isEmpty()) {
                 System.out.println("No existe árbol genealógico de momento.");
                 }
             return;
          }
         String u = prefijo + nodo.getNombre() + " (" + nodo.getCedula() + ", " + nodo.getEdad() + " años)";
         System.out.println(u);
         // Mostrar hijos con indentación aumentada
         mostrarArbol(nodo.getLigalista(), prefijo + "   ");
         // Mostrar hermanos con la misma indentación
         mostrarArbol(nodo.getLiga(), prefijo);
    }
    // Registrar por orden de cedula

    // Actualizar


    // "prueba de github"
}
