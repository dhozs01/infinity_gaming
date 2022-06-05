package com.example.infinitygaming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    Button registerButton;
    EditText nameText, passText, cPassText, telefonoText, dniText, tarjetaText;
    Usuario u;
    ComunicacionServidor comunicacionServidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.registerButton = findViewById(R.id.registerCButton);
        this.nameText = findViewById(R.id.gameNameInput);
        this.passText = findViewById(R.id.passwordInput);
        this.cPassText = findViewById(R.id.cPasswordInput);
        this.telefonoText = findViewById(R.id.priceInput);
        this.dniText = findViewById(R.id.descInput);
        this.tarjetaText = findViewById(R.id.tarjetaInput);

        ArrayList<Usuario> usuarios = new ArrayList<>();

        /*Metodo escuchador del boton de registrarse*/
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comunicacionServidor = new ComunicacionServidor();

                if (passText.getText().toString().equals(cPassText.getText().toString())) {
                    u = new Usuario(null, nameText.getText().toString(), passText.getText().toString(), dniText.getText().toString(), telefonoText.getText().toString());
                    try {
                        comunicacionServidor.insertarUsuario(u);
                        for(Usuario us : comunicacionServidor.leerUsuarios()){
                            if(us.getDni().equals(u.getDni())){
                                Cliente c = new Cliente(us.getIdUsuario(), tarjetaText.getText().toString());
                                comunicacionServidor.insertarCliente(c);
                            }
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(getApplicationContext(), "Cuenta creada con exito", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Register.this, Login.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                }
            }


        });
    }
}