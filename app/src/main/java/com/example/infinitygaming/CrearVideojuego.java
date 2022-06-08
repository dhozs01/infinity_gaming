package com.example.infinitygaming;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Location;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.infinitygaming.excepciones.ExcepcionInfinityGaming;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CrearVideojuego extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    TextView nombreJuegoText,descJuegoText,precioJuegoText;
    Spinner desplegableGenero;
    Button anadirImgButton,borrarImgButton,anadirJuegoButton;
    ComunicacionServidor comunicacionServidor;
    private ImageView imagenJuego;
    final int COD_MARCADA = 10;
    final int COD_FOTO = 20;
    String path = null;
    byte[] inputData;
    ArrayList<Genero> listaGeneros = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_videojuego);

        inicializarComponentes();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


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

    //Metodo que inicializa los componentes de la clase
    private void inicializarComponentes(){
        nombreJuegoText = findViewById(R.id.gameNameInput);
        descJuegoText = findViewById(R.id.descInput);
        precioJuegoText = findViewById(R.id.priceInput);
        desplegableGenero = findViewById(R.id.desplegableGeneros);
        anadirImgButton = findViewById(R.id.anadirImagen);
        borrarImgButton = findViewById(R.id.eliminarImagen);
        anadirJuegoButton = findViewById(R.id.crearVideojuego);
        imagenJuego = findViewById(R.id.gameImageView);

        imagenJuego.setImageDrawable(getResources().getDrawable(R.drawable.avatar));

    }

    //Metodo que implementa la funcionalidad de cargar foto
    private void cargarFoto() {
        final CharSequence[] opc = { "Cargar Foto", "Cancelar"};
        final AlertDialog.Builder alerta_Opc = new AlertDialog.Builder((this));
        alerta_Opc.setTitle("Marque una opción");
        alerta_Opc.setItems(opc, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (opc[which].equals("Cargar Foto")) {
                    seleccionaFoto();
                } else {
                    dialog.dismiss();
                }

            }
        });
        alerta_Opc.show();
    }

    //Metodo que implementa la funcionalidad de seleccionar foto
    private void seleccionaFoto() {
        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        Intent chooserIntent = Intent.createChooser(getIntent(), "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
        startActivityForResult(chooserIntent, COD_MARCADA);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == COD_MARCADA && resultCode == RESULT_OK) {
            Uri miPath = data.getData();
            Glide.with(getBaseContext()).load(miPath).into(imagenJuego);
            try {
                InputStream iStream = getContentResolver().openInputStream(miPath);
                inputData = getBytes(iStream);
                System.out.println(inputData);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (requestCode == COD_FOTO && resultCode == RESULT_OK) {
            MediaScannerConnection.scanFile(this, new String[]{path}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("Ruta de almacenamiento", "path: " + path);
                            Log.i("ExternalStorage", "-> uri=" + uri);
                        }
                    });

            Bitmap bitmap = BitmapFactory.decodeFile(path);
            ExifInterface ei = null;
            try {
                ei = new ExifInterface(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);

            Bitmap rotatedBitmap = null;
            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotatedBitmap = rotateImage(bitmap, 90);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotatedBitmap = rotateImage(bitmap, 180);
                    break;

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotatedBitmap = rotateImage(bitmap, 270);
                    break;

                case ExifInterface.ORIENTATION_NORMAL:
                default:
                    rotatedBitmap = bitmap;
            }

            Glide.with(getBaseContext()).load(rotatedBitmap).into(imagenJuego);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);
            inputData = stream.toByteArray();
        }
    }
    public static Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(),
                matrix, true);
    }




    //Metodo que implementa la funcionalidad de seleccionar imagen
    public void seleccionarImagen(View view) {
        cargarFoto();
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    //Metodo que implementa la funcionalidad del boton confirmar crear Videojuego
    public void confirmar(View view){
        ComunicacionServidor c = new ComunicacionServidor();
        String descrS = descJuegoText.getText().toString();
        Double precioF = null;
        if(!precioJuegoText.getText().toString().isEmpty()) {
            precioF = Double.parseDouble(precioJuegoText.getText().toString());
        }
        Intent i = getIntent();
        int idEmpleado = i.getIntExtra("idEmpleado",0);
        Empleado empleado = null;
        try {
            empleado = c.leerEmpleado(idEmpleado);
            listaGeneros = comunicacionServidor.leerGeneros();
        } catch (ExcepcionInfinityGaming excepcionInfinityGaming) {
            Toast.makeText(getApplicationContext(), "Se ha producido un error al leer empleado", Toast.LENGTH_SHORT).show();
        }

        Genero genero = listaGeneros.get(desplegableGenero.getSelectedItemPosition());
        if(!descrS.isEmpty() && precioF != null && inputData != null){
            Videojuego videojuego = new Videojuego();
            videojuego.setDescripcion(descrS);
            videojuego.setPrecio(precioF);
            videojuego.setImagen(inputData);
            videojuego.setGenero(genero);
            videojuego.setEmpleado(empleado);

            try {
                c.insertarVideojuego(videojuego);
                i = new Intent(CrearVideojuego.this, Tienda.class);
                i.putExtra("idUsuario", idEmpleado);
                i.putExtra("name", empleado.getNombre());
                startActivity(i);
            } catch (ExcepcionInfinityGaming ex) {
                Toast.makeText(getApplicationContext(), ex.getMensajeErrorUsuario(), Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "No puede haber ningún campo vacío", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo que implementa la funcionalidad borrar imagen
    public void borrarImagen(View view) {
        inputData = null;
        imagenJuego.setImageResource(R.drawable.avatar);
    }
}