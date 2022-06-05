package com.example.infinitygaming;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

public class Videojuego implements Serializable {
    private Integer idVideojuego;
    private String descripcion;
    private Double precio;
    private byte[] imagen;

    public Videojuego() {
    }

    public Videojuego(Integer idVideojuego, String descripcion, Double precio, byte[] imagen) {
        this.idVideojuego = idVideojuego;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Videojuego (HashMap map) {
        if(map.containsKey("id")){
            idVideojuego = (Integer) map.get("id");
        } else {
            idVideojuego = null;
        }
        descripcion = (String) map.get("descripcion");
        precio = (Double) map.get("precio");
        imagen = (byte[]) map.get("imagen");
    }

    public HashMap toHash(){
        HashMap map = new HashMap();
        map.put("id", idVideojuego);
        map.put("descripcion", descripcion);
        map.put("precio", precio);
        map.put("imagen", imagen);
        return map;
    }

    public Integer getIdVideojuego() {
        return idVideojuego;
    }

    public void setIdVideojuego(Integer idVideojuego) {
        this.idVideojuego = idVideojuego;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Videojuego{" + "idVideojuego=" + idVideojuego + ", descripcion=" + descripcion + ", precio=" + precio + ", imagen=" + imagen + '}';
    }
}
