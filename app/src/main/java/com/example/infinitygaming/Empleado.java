package com.example.infinitygaming;

import java.util.HashMap;

public class Empleado extends Usuario{
    private Integer idEmpleado;
    private String rol;

    public Empleado() {
    }

    public Empleado(Integer idEmpleado, String Rol) {
        this.idEmpleado = idEmpleado;
        this.rol = Rol;
    }

    public Empleado(Integer idEmpleado, String Rol, Integer idUsuario, String nombre, String apellido, String dni, String telefono) {
        super(idUsuario, nombre, apellido, dni, telefono);
        this.idEmpleado = idEmpleado;
        this.rol = Rol;
    }

    public Empleado (HashMap map) {
        if(map.containsKey("id")){
            idEmpleado = (Integer) map.get("id");
        } else {
            idEmpleado = null;
        }
        rol = (String) map.get("rol");
    }

    public HashMap toHash(){
        HashMap map = new HashMap();
        map.put("id", idEmpleado);
        map.put("nombre", nombre);
        map.put("contrasena", contrasena);
        map.put("dni", dni);
        map.put("telefono", telefono);
        map.put("rol", rol);
        return map;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String Rol) {
        this.rol = Rol;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", Rol=" + rol + '}';
    }
}
