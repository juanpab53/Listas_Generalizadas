package Listas_Generalizadas;

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

    // Gestion personas
    // Registrar por orden de cedula

    // Eliminar desde la cedula

    public void mostrarArbol(Nodo raiz) {
        Nodo nodo = raiz;
        while (nodo != null) {
            if (nodo.getSw() == 0) {
                String u = nodo.getNombre() + " (" + nodo.getCedula() + ", " +
                        nodo.getEdad() + " años)";
                System.out.println(u);

            } else {
                mostrarArbol(nodo.getLigalista());
            }

            nodo = nodo.getLiga();
        }

    }
    /*
     * Según la IA el método de arriba esta malo por el uso del ciclo, se supone
     * deberia quedar de la siguiente manera:
     * 
     * public void mostrarArbol(Nodo nodo, String prefijo) {
     * if (nodo == null) {
     * // Solo mostrar mensaje si es la raíz y no hay árbol
     * if (prefijo.isEmpty()) {
     * JOptionPane.showMessageDialog(null,
     * "No existe árbol genealógico de momento.");
     * }
     * return;
     * }
     * String u = prefijo + nodo.getNombre() + " (" + nodo.getCedula() + ", " +
     * nodo.getEdad() + " años)";
     * JOptionPane.showMessageDialog(null, u);
     * // Mostrar hijos con indentación aumentada
     * mostrarArbol(nodo.getHijo(), prefijo + "   ");
     * // Mostrar hermanos con la misma indentación
     * mostrarArbol(nodo.getHermano(), prefijo);
     * }
     */

}
