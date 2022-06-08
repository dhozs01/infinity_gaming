package com.example.infinitygaming;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.infinitygaming.excepciones.ExcepcionInfinityGaming;

import java.awt.font.TextAttribute;

public class ProductDetails extends AppCompatActivity {

    TextView nombre,precio,descripcion,genero;
    ImageView imagen;
    Button buyButton,modificarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        Intent i = getIntent();
        String price = i.getStringExtra("price");
        String desc = i.getStringExtra("desc");
        byte[] image = i.getByteArrayExtra("image");
        int idVideojuego = i.getIntExtra("idVideojuego",0);
        int idGenero = i.getIntExtra("idGenero", 0);
        String nombreGenero = i.getStringExtra("nombreGenero");
        int idUsuario = i.getIntExtra("idUsuario",0);


        precio = findViewById(R.id.price);
        imagen = findViewById(R.id.imageId);
        buyButton = findViewById(R.id.buyButton);
        descripcion = findViewById(R.id.descId);
        modificarButton = findViewById(R.id.modificarButton);
        genero = findViewById(R.id.genderText);

        comprobarRol(idUsuario);

        precio.setText(price + "€");
        Glide.with(getApplicationContext()).load(image).fitCenter().into(imagen);
        descripcion.setText(desc);
        genero.setText(nombreGenero);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogo();
            }
        });






        modificarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProductDetails.this, ModificarVideojuego.class);
                ComunicacionServidor comunicacionServidor = new ComunicacionServidor();

                i.putExtra("idVideojuego", idVideojuego);
                i.putExtra("idGenero", idGenero);
                startActivity(i);
            }
        });




    }

    //Metodo que comprueba el rol del usuario para mostrar o no el boton de modificar videojuego
    private void comprobarRol(int idEmpleado){
        ComunicacionServidor comunicacionServidor = new ComunicacionServidor();
        Empleado empleado = null;
        try {
            empleado = comunicacionServidor.leerEmpleado(idEmpleado);
        } catch (ExcepcionInfinityGaming excepcionInfinityGaming) {
            excepcionInfinityGaming.printStackTrace();
        }
        if(null != empleado.getRol()){
            modificarButton.setVisibility(View.VISIBLE);
            if(empleado.getRol().equals("Admin") || empleado.getRol().equals("Moderador")){
                modificarButton.setVisibility(View.VISIBLE);
            }
        }

    }

    //Metodo que muestra un dialogo cuando se pulsa en el boton comprar
    private void mostrarDialogo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ProductDetails.this);
        builder.setTitle(R.string.buyComplete);
        builder.setMessage("SDADASDOAH12312")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),R.string.buyComplete,Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();

    }
}