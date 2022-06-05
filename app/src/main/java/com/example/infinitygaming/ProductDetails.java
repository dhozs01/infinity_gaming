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

import java.awt.font.TextAttribute;

public class ProductDetails extends AppCompatActivity {

    TextView nombre,precio,descripcion;
    ImageView imagen;
    Button buyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        Intent i = getIntent();
        String price = i.getStringExtra("price");
        String desc = i.getStringExtra("desc");
        byte[] image = i.getByteArrayExtra("image");




        nombre = findViewById(R.id.nombreJuego);
        precio = findViewById(R.id.price);
        imagen = findViewById(R.id.imageId);
        buyButton = findViewById(R.id.buyButton);
        descripcion = findViewById(R.id.descId);

        precio.setText(price);
        Glide.with(getApplicationContext()).load(image).fitCenter().into(imagen);
        descripcion.setText(desc);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogo();
            }
        });




    }

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