package com.example.infinitygaming;

import java.util.HashMap;

public class Cliente extends Usuario{
    private Integer idCliente;
    private String numeroTarjeta;

    /**
     * Contructor por defecto de la clase Cliente
     */
    public Cliente() {
    }

    public Cliente(Integer idCliente, String numeroTarjeta) {
        this.idCliente = idCliente;
        this.numeroTarjeta = numeroTarjeta;
    }

    /**
     * Metodo para crear un objeto de la clase Cliente
     * @param numeroTarjeta atributo numeroTarjeta de la clase Cliente
     * @param idUsuario atributo idUsuario de la clase Usuario de la cual hereda la clase Cliente
     * @param nombre atributo nombre de la clase Usuario de la cual hereda la clase Cliente
     * @param contrasena atributo apellido de la clase Usuario de la cual hereda la clase Cliente
     * @param dni atributo dni de la clase Usuario de la cual hereda la clase Cliente
     * @param telefono atributo telefono de la clase Usuario de la cual hereda la clase Cliente
     */
    public Cliente( Integer idUsuario, String nombre, String contrasena, String dni, String telefono, String numeroTarjeta) {
        super(idUsuario, nombre,contrasena, dni, telefono);
        this.idCliente = idUsuario;
        this.numeroTarjeta = numeroTarjeta;
    }

    public Cliente (HashMap map) {
        if(map.containsKey("id")){
            idCliente = (Integer) map.get("id");
        } else {
            idCliente = null;
        }
        numeroTarjeta = (String) map.get("numeroTarjeta");
    }

    public HashMap toHash(){
        HashMap map = new HashMap();
        map.put("id", idCliente);
        map.put("nombre", nombre);
        map.put("contrasena", contrasena);
        map.put("dni", dni);
        map.put("telefono", telefono);
        map.put("numeroTarjeta", numeroTarjeta);
        return map;
    }

    /**
     * Getter de idCliente
     * @return atributo idCliente del objeto que llame al metodo
     */
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     * Setter de idCliente
     * @param idCliente id de cliente que le queremos establecer al objeto que llama al metodo
     */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Getter de numeroTarjeta
     * @return atributo numeroTarjeta del objeto que llame al metodo
     */
    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    /**
     * Setter de numeroTarjeta
     * @param numeroTarjeta numero de la tarjeta que le queremos establecer al objeto que llame al metodo
     */
    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    @Override
    public String toString() {
        return "\t" + nombre + "\t" + contrasena + "\t" + dni + "\t" + telefono + "\t" + numeroTarjeta;
    }
}
