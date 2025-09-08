package Listas_Generalizadas;

import javax.swing.*;
import java.util.Scanner;

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
        if (nodo == null) {
            // Solo mostrar mensaje si es la raíz y no hay árbol
            if (prefijo.isEmpty()) {
                System.out.println("No existe árbol genealógico de momento.");
            }
            return;
        }

        String u = prefijo + "- " + nodo.getNombre() + " (" + nodo.getCedula() + ", " + nodo.getEdad() + " años)";
        System.out.println(u);

        // Mostrar hijos con indentación aumentada
        if (nodo.getLigalista() != null) {
            mostrarArbol(nodo.getLigalista(), prefijo + "   ");
        }

        // Mostrar hermanos con la misma indentación
        if (nodo.getLiga() != null) {
            mostrarArbol(nodo.getLiga(), prefijo);
        }
    }


    // Insertar persona
    public void insertarPersona(String nombre, String cedula, int edad, String cedulaPadre) {
        Nodo nuevo = new Nodo(nombre, cedula, edad, null, null);

        if (cabeza == null) {
            cabeza = nuevo;
            System.out.println("Se creó la raíz con " + nombre);
            return;
        }

        Nodo padre = buscarPorCedula(cabeza, cedulaPadre);
        if (padre == null) {
            System.out.println("No se encontró el padre con esa cédula.");
            return;
        }

        if (padre.getLigalista() == null) {
            padre.setLigalista(nuevo);
        } else {
            Nodo hijo = padre.getLigalista();
            while (hijo.getLiga() != null) {
                hijo = hijo.getLiga();
            }
            hijo.setLiga(nuevo);
        }


        padre.setSw(1); // marcar al nodo como padre
        System.out.println(nombre + " fue agregado como hijo de " + padre.getNombre());
    }

    //Mostrar padre
    public void mostrarPadre(String cedulaHijo) {
        Nodo padre = buscarPadre(cabeza, cedulaHijo);
        if (padre == null) {
            System.out.println("No se encontro el padre.");
        }
        else {
            String datos = padre.getNombre() + " (" + padre.getCedula() + ", " + padre.getEdad() + " años)";
            System.out.println("Padre: " + datos);
        }
    }

    // Metodo auxiliar para buscar el padre
    private Nodo buscarPadre(Nodo nodo, String cedulaHijo) {
        if (nodo == null) return null;
        Nodo hijo = nodo.getLigalista();
        while (hijo != null) {
            if (hijo.getCedula().equals(cedulaHijo)) {
                return nodo;
            }
            Nodo encontrado = buscarPadre(hijo, cedulaHijo);
            if (encontrado != null) return encontrado;
            hijo = hijo.getLiga();
        }
        return buscarPadre(nodo.getLiga(), cedulaHijo);
    }

    // Registrar por orden de cedula

    // Actualizar


    // "prueba de github"
}
