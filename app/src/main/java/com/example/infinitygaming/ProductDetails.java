package com.example.infinitygaming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.awt.font.TextAttribute;

public class ProductDetails extends AppCompatActivity {

    TextView nombre,precio,descripcion;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String price = i.getStringExtra("price");
        String desc = i.getStringExtra("desc");
        int image = i.getIntExtra("image",R.drawable.corekeeper);

        nombre = findViewById(R.id.nombreJuego);
        precio = findViewById(R.id.price);
        imagen = findViewById(R.id.imageId);
        descripcion = findViewById(R.id.descId);
        precio.setText(price);
        nombre.setText(name);
        imagen.setImageResource(image);
        descripcion.setText(desc);


    }
}