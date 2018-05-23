package com.example.john.labo7sqlite.Object;

public class Personas {

    private String Carnet;
    private String Nombre;
    private String Nota;

    public Personas() {

    }

    public Personas(String Carnet, String Nombre, String Nota) {
        this.Carnet = Carnet;
        this.Nombre = Nombre;
        this.Nota = Nota;
    }

    public String getCarnet() {
        return Carnet;
    }

    public void setCarnet(String carnet) {
        Carnet = carnet;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String nota) {
        Nota = nota;
    }
}
