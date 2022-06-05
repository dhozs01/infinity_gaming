package com.example.infinitygaming;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public class Usuario implements Serializable {

    protected Integer idUsuario;
    protected String nombre;
    protected String contrasena;
    protected String dni;
    protected String telefono;

    /**
     * Constructor por defecto de la clase Usuario
     */
    public Usuario() {

    }

    /**
     * Metodo para crear un objeto de la clase Usuario
     * @param idUsuario atributo idUsuario del objeto que queremos crear
     * @param nombre atributo nombre del objeto que queremos crear
     * @param contrasena atributo dni del objeto que queremos crear
     * @param dni atributo dni del objeto que queremos crear
     * @param telefono atributo telefono del objeto que queremos crear
     */
    public Usuario(Integer idUsuario, String nombre, String contrasena, String dni, String telefono) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.dni = dni;
        this.telefono = telefono;
    }

    public Usuario(Integer idUsuario, String nombre, String contrasena) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public Usuario (HashMap map) {
        if(map.containsKey("id")){
            idUsuario = (Integer) map.get("id");
        } else {
            idUsuario = null;
        }
        nombre = (String) map.get("nombre");
        contrasena = (String) map.get("contrasena");
        dni = (String) map.get("dni");
        telefono = (String) map.get("telefono");
    }

    public HashMap toHash(){
        HashMap map = new HashMap();
        map.put("id", idUsuario);
        map.put("nombre", nombre);
        map.put("contrasena", contrasena);
        map.put("dni", dni);
        map.put("telefono", telefono);
        return map;
    }

    /**
     * Getter de idUsuario
     * @return atributo idUsuario del objeto que llame al metodo
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * Setter de idUsuario
     * @param idUsuario id de usuario que le queremos establecer al objeto que llama al método
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Getter de nombre
     * @return atributo nombre del objeto que llame al metodo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter de nombre
     * @param nombre atributo nombre que le queremos establecer al objeto que llama al metodo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter de apellido
     * @return atributo apellido del objeto que llame al método
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Setter de apellido
     * @param apellido atributo apellido que le queremos establecer al objeto que llama al método
     */
    public void setContrasena(String apellido) {
        this.contrasena = apellido;
    }

    /**
     * Getter de dni
     * @return atributo dni del objeto que llame al método
     */
    public String getDni() {
        return dni;
    }

    /**
     * Setter de dni
     * @param dni atributo dni que le queremos establecer al objeto que llama al método
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Getter de telefono
     * @return atributo telefono del objeto que llame al método
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Setter de telefono
     * @param telefono atributo telefono que le queremos establecer al objeto que llama al método
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "\t" + nombre + "\t" + contrasena + "\t" + dni + "\t" + telefono;
    }
}
