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

    //1. Mostrar todos los datos del arbol
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

    //2. insertar persona
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

    //3. Actualizar datos
    public void actualizarEdad(String cedula, int nuevaEdad) {
        Nodo nodo = buscarPorCedula(cabeza, cedula);
        if (nodo != null) {
            nodo.setEdad(nuevaEdad);
            System.out.println("Edad actualizada a: " + nuevaEdad);
        } else {
            System.out.println("No se encontró el nodo con la cédula proporcionada.");
        }
    }

    public void actualizarNombre(String cedula, String nuevoNombre) {
        Nodo nodo = buscarPorCedula(cabeza, cedula);
        if (nodo != null) {
            nodo.setNombre(nuevoNombre);
            System.out.println("Nombre actualizado a: " + nuevoNombre);
        } else {
            System.out.println("No se encontró el nodo con la cédula proporcionada.");
        }
    }

    //4. Mostrar padre
    public void mostrarPadre(String cedulaHijo) {
        Nodo padre = buscarPadre(cabeza, cedulaHijo);
        if (padre == null) {
            System.out.println("No se encontro el padre.");
        } else {
            String datos = padre.getNombre() + " (" + padre.getCedula() + ", " + padre.getEdad() + " años)";
            System.out.println("Padre: " + datos);
        }
    }

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

    // 5. Mostrar hijos
    public void mostrarHijos(String cedulaPadre) {
        Nodo padre = buscarPorCedula(cabeza, cedulaPadre);
        if (padre == null) {
            System.out.println("No se encontró el padre con esa cédula.");
            return;
        }
        if(padre.getLigalista()==null)
        {
            System.out.println(padre.getNombre() + " no tiene hijos.");
            return;
        }
        System.out.println("Hijos de "+padre.getNombre()+":");
        Nodo hijo = padre.getLigalista();
        while(hijo != null) {
            String datos = hijo.getNombre() + " (" + hijo.getCedula() + ", " + hijo.getEdad() + " años)";
            System.out.println("- " + datos);
            hijo = hijo.getLiga();
        }
    }
    //6. Mostrar  hermanos
    public void mostrarHermanos(String cedula) {
        Nodo persona = buscarPorCedula(cabeza, cedula);
        if (persona == null) {
            System.out.println("No se encontro la persona con esa cedula");
            return;
        }

        Nodo padre = buscarPadre(cabeza, cedula);
        if (padre == null) {
            System.out.println(persona.getNombre() + " no tiene padre registrado, por lo tanto no tiene hermanos.");
            return;
        }

        Nodo hermano = padre.getLigalista();
        boolean tieneHermanos = false;

        System.out.println("hermanos de " + persona.getNombre() + ":");
        while (hermano != null) {
            if (!hermano.getCedula().equals(cedula)) { // evitar mostrar a la misma persona
                String datos = hermano.getNombre() + " (" + hermano.getCedula() + ", " + hermano.getEdad() + " años)";
                System.out.println("- " + datos);
                tieneHermanos = true;
            }
            hermano = hermano.getLiga();
        }

        if (!tieneHermanos) {
            System.out.println(persona.getNombre() + " no tiene hermanos.");
        }
    }

    // 7. Mostrar ancestros
    public void mostrarAncestros(String cedula) {
        Nodo nodo = buscarPorCedula(cabeza, cedula);
        if (nodo == null) {
            System.out.println("No se encontró la persona con esa cédula.");
            return;
        }
        System.out.println("Ancestros de " + nodo.getNombre() + ":");
        mostrarAncestrosRecursivo(cabeza, cedula);
    }

    public void mostrarAncestrosRecursivo(Nodo nodo, String cedula) {
        Nodo padre = buscarPadre(nodo, cedula);
        if (padre != null) {
            System.out.println("- " + padre.getNombre() + " (" + padre.getCedula() + ", " + padre.getEdad() + " años)");
            mostrarAncestrosRecursivo(nodo, padre.getCedula());
        }
    }

    // 8. Mostrar descendientes
    public void mostrarDescendientes(String cedula) {
        Nodo nodo = buscarPorCedula(cabeza, cedula);
        if (nodo == null) {
            System.out.println("No se encontró la persona con esa cédula.");
            return;
        }
        System.out.println("Descendientes de " + nodo.getNombre() + ":");
        mostrarDescendientesRecursivo(nodo.getLigalista(), "  ");
    }

    private void mostrarDescendientesRecursivo(Nodo nodo, String prefijo) {
        while (nodo != null) {
            System.out.println(prefijo + "- " + nodo.getNombre() +
                    " (" + nodo.getCedula() + ", " + nodo.getEdad() + " años)");
            // Si tiene hijos, se llama recursivamente
            if (nodo.getLigalista() != null) {
                mostrarDescendientesRecursivo(nodo.getLigalista(), prefijo + "  ");
            }
            nodo = nodo.getLiga(); // pasar al siguiente hermano
        }
    }

    // 9. Mostrar nodo con mayor grado
    public void mostrarNodoMayorGrado() {
        if (cabeza == null) {
            System.out.println("El árbol está vacío.");
            return;
        }
        Nodo[] resultado = new Nodo[1]; // para guardar el nodo con mayor grado
        int[] maxGrado = { -1 };        // para guardar el grado máximo encontrado
        calcularMayorGrado(cabeza, resultado, maxGrado);
        if (resultado[0] != null) {
            Nodo nodo = resultado[0];
            System.out.println("Nodo con mayor grado:");
            System.out.println(nodo.getNombre() + " (" + nodo.getCedula() + ", " + nodo.getEdad() + " años) - Grado " + maxGrado[0]);
        }
    }

    //Calcula el mayor grado
    private void calcularMayorGrado(Nodo actual, Nodo[] resultado, int[] maxGrado) {
        if (actual == null) return;
        int grado = 0;
        Nodo hijo = actual.getLigalista();
        while (hijo != null) {
            grado++;
            hijo = hijo.getLiga();
        }
        if (grado > maxGrado[0]) {
            maxGrado[0] = grado;
            resultado[0] = actual;
        }
        // recorrer hijos
        calcularMayorGrado(actual.getLigalista(), resultado, maxGrado);
        // recorrer hermanos (mismo nivel)
        calcularMayorGrado(actual.getLiga(), resultado, maxGrado);
    }

    // 10. Mostrar nodo con mayor nivel
    public void mostrarNodoMayorNivel() {
        if (cabeza == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        Nodo[] resultado = new Nodo[1]; // para guardar el nodo con mayor nivel
        int[] maxNivel = { -1 };        // para guardar el nivel máximo encontrado

        calcularMayorNivel(cabeza, 0, resultado, maxNivel);

        if (resultado[0] != null) {
            Nodo nodo = resultado[0];
            System.out.println("Nodo con mayor nivel:");
            System.out.println(nodo.getNombre() + " (" + nodo.getCedula() + ", " + nodo.getEdad() + " años) - Nivel " + maxNivel[0]);
        }
    }

    private void calcularMayorNivel(Nodo actual, int nivel, Nodo[] resultado, int[] maxNivel) {
        if (actual == null) return;

        if (nivel > maxNivel[0]) {
            maxNivel[0] = nivel;
            resultado[0] = actual;
        }

        // recorrer hijos
        calcularMayorNivel(actual.getLigalista(), nivel + 1, resultado, maxNivel);

        // recorrer hermanos (mismo nivel)
        calcularMayorNivel(actual.getLiga(), nivel, resultado, maxNivel);
    }

    // 11. mostrar altura del arbol
    public int alturaArbol() {
        int aux = calcularAltura(cabeza);
        if (aux == -1) {
            System.out.println("El árbol está vacío.");
        }
        return aux;
    }

    private int calcularAltura(Nodo nodo) {
        if (nodo == null) return -1; // altura de árbol vacío es -1

        int alturaMaxima = -1;
        Nodo hijo = nodo.getLigalista();
        while (hijo != null) {
            int alturaHijo = calcularAltura(hijo);
            if (alturaHijo > alturaMaxima) {
                alturaMaxima = alturaHijo;
            }
            hijo = hijo.getLiga();
        }
        return alturaMaxima + 1; // sumar 1 por el nivel actual
    }

    // 12. Mostrar registros de un nivel dado
    public void mostrarNivelRegistro(String cedula) {
        int nivel = buscarNivel(cabeza, cedula, 0);
        if (nivel == -1) {
            System.out.println("No se encontró un registro con la cédula " + cedula);
        } else {
            System.out.println("El registro con cédula " + cedula + " está en el nivel " + nivel);
        }
    }

    // Metodo recursivo que busca el nodo y devuelve su nivel, o -1 si no está
    private int buscarNivel(Nodo actual, String cedula, int nivel) {
        if (actual == null) return -1;

        // comparar como String (usar equals)
        if (actual.getCedula() != null && actual.getCedula().equals(cedula)) {
            return nivel; // encontrado
        }

        // Buscar en los hijos (nivel + 1)
        int nivelHijo = buscarNivel(actual.getLigalista(), cedula, nivel + 1);
        if (nivelHijo != -1) {
            return nivelHijo;
        }

        // Buscar en los hermanos (mismo nivel)
        return buscarNivel(actual.getLiga(), cedula, nivel);
    }


    // 13. Mostrar registros de un nivel dado
    public void mostrarRegistrosNivel(int nivelObjetivo) {
        if (cabeza == null) {
            System.out.println("El arbol esta vacio.");
            return;
        }
        System.out.println("Registros en el nivel " + nivelObjetivo + ":");
        mostrarRegistrosNivelRec(cabeza, 0, nivelObjetivo);
    }
    private void mostrarRegistrosNivelRec(Nodo actual, int nivel, int nivelObjetivo) {
        if (actual == null) return;

        if (nivel == nivelObjetivo) {
            String datos = actual.getNombre() + " (" + actual.getCedula() + ", " + actual.getEdad() + " años)";
            System.out.println("- " + datos);
        }

        // procesar hijos (nivel+1)
        mostrarRegistrosNivelRec(actual.getLigalista(), nivel + 1, nivelObjetivo);

        // procesar hermanos (mismo nivel)
        mostrarRegistrosNivelRec(actual.getLiga(), nivel, nivelObjetivo);
    }

    // Eliminar todos los nodos de un nivel dado con reestructuración
    public void eliminarNivel(int nivelObjetivo) {
        if (cabeza == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        if (nivelObjetivo == 0) {
            System.out.println("No se puede eliminar la raíz con esta reestructuración.");
            return;
        }

        // Comenzar desde la raíz
        cabeza.setLigalista(eliminarNivelRec(cabeza.getLigalista(), 1, nivelObjetivo));
        cabeza.setLiga(eliminarNivelRec(cabeza.getLiga(), 0, nivelObjetivo));

        actualizarSw(cabeza);

        System.out.println("Nivel " + nivelObjetivo + " eliminado con reestructuración.");
    }

    // 🔄 Metodo recursivo: procesa la lista de nodos hermanos en un nivel
    private Nodo eliminarNivelRec(Nodo actual, int nivel, int nivelObjetivo) {
        if (actual == null) return null;

        Nodo anterior = null;
        Nodo primero = actual; // referencia al inicio de la lista de hermanos

        while (actual != null) {
            if (nivel == nivelObjetivo) {
                // Estamos en el nivel a eliminar
                Nodo siguiente = actual.getLiga(); // siguiente hermano

                if (actual.getLigalista() == null) {
                    // Caso: no tiene hijos → se borra el nodo
                    if (anterior == null) {
                        primero = siguiente;
                    } else {
                        anterior.setLiga(siguiente);
                    }
                } else {
                    // Caso: tiene hijos → el primer hijo sube en lugar del nodo eliminado
                    Nodo primerHijo = actual.getLigalista();
                    Nodo ultimoHijo = primerHijo;

                    while (ultimoHijo.getLiga() != null) {
                        ultimoHijo = ultimoHijo.getLiga();
                    }

                    // Conectar el último hijo con los hermanos que venían después
                    ultimoHijo.setLiga(siguiente);

                    if (anterior == null) {
                        primero = primerHijo;
                    } else {
                        anterior.setLiga(primerHijo);
                    }

                    // El último hijo pasa a ser el "nuevo anterior"
                    anterior = ultimoHijo;
                }

                // Pasamos al siguiente hermano original
                actual = siguiente;
            } else {
                // Aún no estamos en el nivel → bajamos a los hijos
                actual.setLigalista(eliminarNivelRec(actual.getLigalista(), nivel + 1, nivelObjetivo));

                // Seguimos recorriendo los hermanos
                anterior = actual;
                actual = actual.getLiga();
            }
        }

        return primero;
    }

    // Recalcular el sw en en el arbol
    private void actualizarSw(Nodo nodo) {
        if (nodo == null) return;
        nodo.setSw(nodo.getLigalista() != null ? 1 : 0);
        actualizarSw(nodo.getLigalista());
        actualizarSw(nodo.getLiga());
    }


}
