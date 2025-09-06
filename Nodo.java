package Listas_Generalizadas;

public class Nodo {
    // Atributos
    private int sw;
    private String nombre;
    private String cedula;
    private int edad;
    private Nodo liga;
    private Nodo ligalista;

    // Constructor
    public Nodo() {
        sw = 0;
        nombre = "";
        cedula = "";
        edad = 0;
        liga = null;
        ligalista = null;
    }

    // Por si se desea de ingresar un nuevo nodo con datos
    public Nodo(String nombre, String cedula, int edad, Nodo liga, Nodo ligalista) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.liga = liga;
        this.ligalista = ligalista;
    }

    // Getters y Setters
    // sw
    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    // nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // cedula
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    // edad
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    // hijo
    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }

    // hermano
    public Nodo getLigalista() {
        return ligalista;
    }

    public void setLigalista(Nodo ligalista) {
        this.ligalista = ligalista;
    }
}