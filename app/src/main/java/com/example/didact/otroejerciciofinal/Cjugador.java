package com.example.didact.otroejerciciofinal;

/**
 * Created by DIDACT on 23/02/2018.
 */

public class Cjugador {

    String nombre;
    int dorsal;
    String posicion;
    int sueldo;

    public Cjugador() {

    }
    public Cjugador(String nombre, int dorsal, String posicion, int sueldo) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.sueldo = sueldo;


    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }


}
