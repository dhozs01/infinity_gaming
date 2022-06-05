package com.example.infinitygaming;

import java.io.Serializable;
import java.util.HashMap;

public class Genero implements Serializable {

    private Integer generoId;
    private String nombre;

    public Genero() {
    }

    public Genero(Integer generoId){
        this.generoId = generoId;
    }
    public Genero(String nombre) {
        this.nombre = nombre;
    }

    public Genero(Integer generoId, String nombre) {
        this.generoId = generoId;
        this.nombre = nombre;
    }







    public Integer getGeneroId() {
        return this.generoId;
    }

    public void setGeneroId(Integer generoId) {
        this.generoId = generoId;
    }



    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap toHash(){
        HashMap map = new HashMap();
        map.put("id", generoId);
        map.put("nombre", nombre);
        return map;
    }

    public Genero(HashMap map){
        if(map.containsKey("id")){
            generoId = (Integer) map.get("id");
        }else{
            generoId = null;
        }
        nombre = (String) map.get("nombre");
    }


    @Override
    public String toString() {
        return this.nombre;
    }
}
