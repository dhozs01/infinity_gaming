package com.example.infinitygaming;

import java.io.Serializable;
import java.util.HashMap;

public class Genero implements Serializable {
    private Integer idGenero;
    private String nombre;

    public Genero() {

    }

    public Genero(Integer idGenero, String nombre) {
        this.idGenero = idGenero;
        this.nombre = nombre;
    }

    public Genero (HashMap map) {
        if(map.containsKey("id")){
            idGenero = (Integer) map.get("id");
        } else {
            idGenero = null;
        }
        nombre = (String) map.get("nombre");
    }

    public HashMap toHash(){
        HashMap map = new HashMap();
        map.put("id", idGenero);
        map.put("nombre", nombre);
        return map;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Genero{" + "idGenero=" + idGenero + ", nombre=" + nombre + '}';
    }
}
