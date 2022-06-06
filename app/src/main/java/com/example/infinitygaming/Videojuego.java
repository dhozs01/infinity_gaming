package com.example.infinitygaming;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

public class Videojuego implements Serializable {
    private Integer idVideojuego;
    private String descripcion;
    private Double precio;
    private byte[] imagen;
    private Cliente cliente;
    private Genero genero;
    private Empleado empleado;

    public Videojuego() {
    }

    public Videojuego(Integer idVideojuego, String descripcion, Double precio, byte[] imagen, Cliente cliente, Genero genero, Empleado empleado) {
        this.idVideojuego = idVideojuego;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
        this.cliente = cliente;
        this.genero = genero;
        this.empleado = empleado;
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
        cliente = new Cliente();
        cliente.setIdCliente((Integer) map.get("id_cliente"));
        genero = new Genero();
        genero.setIdGenero((Integer) map.get("id_genero"));
        empleado = new Empleado();
        empleado.setIdEmpleado((Integer) map.get("id_empleado"));
    }

    public HashMap toHash(){
        HashMap map = new HashMap();
        map.put("id", idVideojuego);
        map.put("descripcion", descripcion);
        map.put("precio", precio);
        map.put("imagen", imagen);
        map.put("id_genero", genero.getIdGenero());
        map.put("id_empleado", empleado.getIdEmpleado());
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

    public Double getPrecio() {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "Videojuego{" + "idVideojuego=" + idVideojuego + ", descripcion=" + descripcion + ", precio=" + precio + ", imagen=" + imagen + ", cliente=" + cliente + ", genero=" + genero + ", empleado=" + empleado + '}';
    }
}
