package com.example.infinitygaming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CrearEmpleado extends AppCompatActivity {

    Spinner desplegableRoles;
    Button añadirEmpleadoButton;
    ComunicacionServidor comunicacionServidor;
    TextView nombreText,passText,passCText,dniText,telefonoText;
    Usuario u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_empleado);

        desplegableRoles = findViewById(R.id.desplegableGeneros);
        añadirEmpleadoButton = findViewById(R.id.anadirEButton);
        nombreText = findViewById(R.id.gameNameInput);
        passText = findViewById(R.id.passwordInput);
        passCText = findViewById(R.id.cPasswordInput);
        dniText = findViewById(R.id.descInput);
        telefonoText = findViewById(R.id.priceInput);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desplegableRoles.setAdapter(spinnerAdapter);
        spinnerAdapter.add("Admin");
        spinnerAdapter.add("Empleado");
        spinnerAdapter.add("Moderador");
        spinnerAdapter.notifyDataSetChanged();



        /*Metodo escuchador del boton de registrarse*/
        añadirEmpleadoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comunicacionServidor = new ComunicacionServidor();

                if (passText.getText().toString().equals(passCText.getText().toString())) {
                    u = new Usuario(null, nombreText.getText().toString(), passText.getText().toString(), dniText.getText().toString(), telefonoText.getText().toString());
                    try {
                        comunicacionServidor.insertarUsuario(u);
                        for(Usuario us : comunicacionServidor.leerUsuarios()){
                            if(us.getDni().equals(u.getDni())){
                                Empleado e = new Empleado(us.getIdUsuario(), desplegableRoles.getSelectedItem().toString());
                                comunicacionServidor.insertarEmpleado(e);
                            }
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(getApplicationContext(), "Cuenta creada con exito", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                }
            }


        });

    }
}