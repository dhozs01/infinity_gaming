package com.example.infinitygaming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.infinitygaming.excepciones.ExcepcionInfinityGaming;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    Button registerButton,loginButton;
    EditText name;
    EditText pass;
    ComunicacionServidor comunicacionServidor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.registerButton = findViewById(R.id.registerButton);
        this.loginButton = findViewById(R.id.loginButton);
        this.name = findViewById(R.id.nameInput);
        this.pass = findViewById(R.id.passInput);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comunicacionServidor = new ComunicacionServidor();

                try {
                    ArrayList<Usuario> listaUsuarios = comunicacionServidor.leerUsuarios();
                    for(Usuario u: listaUsuarios){
                        if(u.getNombre().equals(name.getText().toString()) && u.getContrasena().equals(pass.getText().toString())){
                            Toast.makeText(getApplicationContext(), "Bienvenido de nuevo " + u.getNombre() , Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Login.this, Tienda.class);
                            i.putExtra("name", String.valueOf(name.getText()));
                            i.putExtra("idUsuario",  u.getIdUsuario());
                            startActivity(i);
                            return;
                        }
                    }
                    Toast.makeText(getApplicationContext(), "El usuario o contraseña son incorrectos", Toast.LENGTH_SHORT).show();

                }catch(ExcepcionInfinityGaming ex){
                    Toast.makeText(getApplicationContext(), ex.getMensajeErrorUsuario(), Toast.LENGTH_SHORT).show();
                    System.out.println(ex.getMensajeErrorBD());
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }


}