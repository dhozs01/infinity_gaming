package com.example.infinitygaming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infinitygaming.adapter.VideojuegoAdapter;
import com.example.infinitygaming.excepciones.ExcepcionInfinityGaming;

import java.util.ArrayList;
import java.util.List;

public class Tienda extends AppCompatActivity implements SearchView.OnQueryTextListener{

    RecyclerView videojuegoRecyclerView;
    VideojuegoAdapter videojuegoAdapter;
    List<Videojuego> listaVideojuegos;
    TextView nameText;
    SearchView searchView;
    ComunicacionServidor comunicacionServidor;
    ImageButton addEmpleadoButton,addVideojuegoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        videojuegoRecyclerView = findViewById(R.id.trendyRecycler);
        nameText = findViewById(R.id.nameText);
        searchView = findViewById(R.id.searchView);
        addEmpleadoButton = findViewById(R.id.addEmpleadoButton);
        addVideojuegoButton = findViewById(R.id.addVideojuegoButton);


        addVideojuegoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Tienda.this, CrearVideojuego.class);
                startActivity(i);
            }
        });

        addEmpleadoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Tienda.this, CrearEmpleado.class);
                startActivity(i);
            }
        });


        Intent i = getIntent();
        nameText.setText(i.getStringExtra("name"));
        int idUsuario =  i.getIntExtra("idUsuario",0);


        try {
            comunicacionServidor = new ComunicacionServidor();
            comprobarRol(idUsuario);
        } catch (ExcepcionInfinityGaming excepcionInfinityGaming) {
            excepcionInfinityGaming.printStackTrace();
        }


        try {

            listaVideojuegos = comunicacionServidor.leerVideojuegos();
        } catch (ExcepcionInfinityGaming excepcionInfinityGaming) {
            excepcionInfinityGaming.printStackTrace();
        }
        setVideojuegoRecycler(listaVideojuegos);

        searchView.setOnQueryTextListener(this);
    }





    private void setVideojuegoRecycler(List<Videojuego> dataList){
        videojuegoRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        videojuegoAdapter = new VideojuegoAdapter(this,dataList);
        videojuegoRecyclerView.setAdapter(videojuegoAdapter);
    }

    private void comprobarRol(int idUsuario) throws ExcepcionInfinityGaming {
        Empleado empleado = comunicacionServidor.leerEmpleado(idUsuario);
        if(null != empleado.getRol()){
            addVideojuegoButton.setVisibility(View.VISIBLE);
            if(empleado.getRol().equals("Admin")){
                addEmpleadoButton.setVisibility(View.VISIBLE);
            }
        }

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        videojuegoAdapter.filtrado(newText);
        return false;
    }
}