package com.example.infinitygaming;

import com.example.infinitygaming.excepciones.ExcepcionInfinityGaming;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class ComunicacionServidor {

    private final String IP = "192.168.1.182";
    private final Integer PUERTO = 30500;
    private  Object resultado;
    private ArrayList<HashMap> listaHash;
    private ExcepcionInfinityGaming excepcionInfinityGaming;
    private HashMap objetoRespuesta;
    private String respuesta;

    public ComunicacionServidor() {

    }

    public String insertarUsuario(final Usuario u) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "insertar usuario");
                peticion.put("argumento", u.toHash());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }

    public String modificarUsuario(final Usuario u) throws ExcepcionInfinityGaming {

        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();
                peticion.put("peticion", "modificar usuario");
                peticion.put("argumento", u.toHash());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }

    public String borrarUsuario(final Usuario u) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "eliminar usuario");
                peticion.put("argumento", u.getIdUsuario());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }

    public ArrayList<Usuario> leerUsuarios() throws ExcepcionInfinityGaming {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap peticion = new HashMap();
                peticion.put("peticion", "leer usuarios");
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof ArrayList){
                        listaHash = (ArrayList<HashMap>) resultado;
                    }else if (resultado instanceof Exception){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }).start();
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        while (excepcionInfinityGaming == null && listaHash == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;

        for (HashMap map : listaHash) {
            Usuario u = new Usuario(map);
            listaUsuarios.add(u);
        }

        return listaUsuarios;
    }

    public Usuario leerUsuario(final Integer id) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap peticion = new HashMap();
                peticion.put("peticion", "leer usuario");
                peticion.put("argumento", id);
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof HashMap){
                        objetoRespuesta = (HashMap) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }).start();
        while (excepcionInfinityGaming == null && objetoRespuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;

        return new Usuario(objetoRespuesta);
    }

    public String insertarVideojuego(final Videojuego v) throws ExcepcionInfinityGaming {

        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "insertar videojuego");
                peticion.put("argumento", v.toHash());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }

    public String borrarVideojuego(final Videojuego v) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "eliminar videojuego");
                peticion.put("argumento", v.getIdVideojuego());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }

    public String modificarVideojuego(final Videojuego v) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "modificar producto");
                peticion.put("argumento", v.toHash());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }

    public ArrayList<Videojuego> leerVideojuegos() throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap peticion = new HashMap();
                peticion.put("peticion", "leer videojuegos");
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof ArrayList){
                        listaHash = (ArrayList<HashMap>) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }).start();
        ArrayList<Videojuego> listaVideojuegos = new ArrayList<>();
        while (excepcionInfinityGaming == null && listaHash == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;

        for (HashMap map : listaHash) {
            Videojuego v = new Videojuego(map);
            listaVideojuegos.add(v);
        }

        return listaVideojuegos;
    }

    public Videojuego leerVideojuego(final Integer id) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap peticion = new HashMap();
                peticion.put("peticion", "leer videojuego");
                peticion.put("argumento", id);
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof HashMap){
                        objetoRespuesta = (HashMap) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }).start();
        while (excepcionInfinityGaming == null && objetoRespuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;

        return new Videojuego(objetoRespuesta);
    }

    public String insertarGenero(final Genero g) throws ExcepcionInfinityGaming {

        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "insertar genero");
                peticion.put("argumento", g.toHash());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }

    public String borrarGenero(final Genero g) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "eliminar genero");
                peticion.put("argumento", g.getGeneroId());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }

    public String modificarGenero(final Genero g) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "modificar genero");
                peticion.put("argumento", g.toHash());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }


    public ArrayList<Genero> leerGeneros() throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap peticion = new HashMap();
                peticion.put("peticion", "leer generos");
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof ArrayList){
                        listaHash = (ArrayList<HashMap>) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }).start();
        ArrayList<Genero> listaGeneros = new ArrayList<>();
        while (excepcionInfinityGaming == null && listaHash == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;

        for (HashMap map : listaHash) {
            Genero g = new Genero(map);
            listaGeneros.add(g);
        }

        return listaGeneros;
    }

    public Genero leerGenero(final Integer id) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap peticion = new HashMap();
                peticion.put("peticion", "leer genero");
                peticion.put("argumento", id);
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof HashMap){
                        objetoRespuesta = (HashMap) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }).start();
        while (excepcionInfinityGaming == null && objetoRespuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;

        return new Genero(objetoRespuesta);
    }

    public String insertarCliente(final Cliente c) throws ExcepcionInfinityGaming {

        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "insertar cliente");
                peticion.put("argumento", c.toHash());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }

    public String borrarCliente(final Cliente c) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "eliminar genero");
                peticion.put("argumento", c.getIdCliente());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }

    public String modificarCliente(final Cliente c) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "modificar cliente");
                peticion.put("argumento", c.toHash());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }


    public ArrayList<Cliente> leerClientes() throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap peticion = new HashMap();
                peticion.put("peticion", "leer clientes");
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof ArrayList){
                        listaHash = (ArrayList<HashMap>) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }).start();
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        while (excepcionInfinityGaming == null && listaHash == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;

        for (HashMap map : listaHash) {
            Cliente c = new Cliente(map);
            listaClientes.add(c);
        }

        return listaClientes;
    }

    public Cliente leerCliente(final Integer id) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap peticion = new HashMap();
                peticion.put("peticion", "leer cliente");
                peticion.put("argumento", id);
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof HashMap){
                        objetoRespuesta = (HashMap) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }).start();
        while (excepcionInfinityGaming == null && objetoRespuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;

        return new Cliente(objetoRespuesta);
    }

    public String insertarEmpleado(final Empleado e) throws ExcepcionInfinityGaming {

        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "insertar empleado");
                peticion.put("argumento", e.toHash());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }

    public String borrarEmpleado(final Empleado e) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "eliminar empleado");
                peticion.put("argumento", e.getIdEmpleado());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }

    public String modificarEmpleado(final Empleado e) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {

            @Override
            public void run() {
                HashMap peticion = new HashMap();

                peticion.put("peticion", "modificar empleado");
                peticion.put("argumento", e.toHash());
                resultado = null;
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof String){
                        respuesta = (String) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (excepcionInfinityGaming == null && respuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;


        return respuesta;
    }


    public ArrayList<Empleado> leerEmpleados() throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap peticion = new HashMap();
                peticion.put("peticion", "leer empleados");
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof ArrayList){
                        listaHash = (ArrayList<HashMap>) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }).start();
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        while (excepcionInfinityGaming == null && listaHash == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;

        for (HashMap map : listaHash) {
            Empleado e = new Empleado(map);
            listaEmpleados.add(e);
        }

        return listaEmpleados;
    }

    public Empleado leerEmpleado(final Integer id) throws ExcepcionInfinityGaming {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap peticion = new HashMap();
                peticion.put("peticion", "leer empleado");
                peticion.put("argumento", id);
                try {
                    Socket socket = new Socket(IP, PUERTO);
                    ObjectOutputStream flujoSalida = new ObjectOutputStream(socket.getOutputStream());
                    flujoSalida.writeObject(peticion);
                    flujoSalida.flush();
                    ObjectInputStream flujoEntrada = new ObjectInputStream(socket.getInputStream());
                    resultado = flujoEntrada.readObject();
                    if(resultado instanceof HashMap){
                        objetoRespuesta = (HashMap) resultado;
                    }else if (resultado instanceof ExcepcionInfinityGaming){
                        excepcionInfinityGaming = (ExcepcionInfinityGaming) resultado;
                    }
                    flujoEntrada.close();
                    flujoSalida.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


            }
        }).start();
        while (excepcionInfinityGaming == null && objetoRespuesta == null) {
            System.out.printf("");
        }
        if (excepcionInfinityGaming != null)
            throw excepcionInfinityGaming;

        return new Empleado(objetoRespuesta);
    }


}
