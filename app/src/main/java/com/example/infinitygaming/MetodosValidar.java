package com.example.infinitygaming;

public class MetodosValidar {
    /*Metodo que valida el nombre de usuario*/
    public static boolean isUsernameValid(String username){
        if (username == null){
            return false;
        }
        else if (username.length() > 20 || username.length() < 3){
            return false;
        }else{
            return true;
        }

    }

    /*Metodo que valida la contraseña*/
    public static boolean isPasswordValid(String password){
        return password != null && password.trim().length() > 5;

    }
}
