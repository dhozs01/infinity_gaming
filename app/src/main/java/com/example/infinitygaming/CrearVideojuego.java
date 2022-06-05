package com.example.infinitygaming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.infinitygaming.excepciones.ExcepcionInfinityGaming;

import java.util.ArrayList;

public class CrearVideojuego extends AppCompatActivity {

    TextView nombreJuegoText,descJuegoText,precioJuegoText;
    Spinner desplegableGenero;
    ComunicacionServidor comunicacionServidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_videojuego);

        nombreJuegoText = findViewById(R.id.gameNameInput);
        descJuegoText = findViewById(R.id.descInput);
        precioJuegoText = findViewById(R.id.priceInput);
        desplegableGenero = findViewById(R.id.desplegableGeneros);

        comunicacionServidor = new ComunicacionServidor();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        desplegableGenero.setAdapter(spinnerAdapter);
        try {
            ArrayList<Genero> listaGeneros = comunicacionServidor.leerGeneros();
            for(Genero g: listaGeneros){
                spinnerAdapter.add(g.getNombre());
            }
        } catch (ExcepcionInfinityGaming excepcionInfinityGaming) {
            excepcionInfinityGaming.printStackTrace();
        }

        spinnerAdapter.notifyDataSetChanged();
    }
}