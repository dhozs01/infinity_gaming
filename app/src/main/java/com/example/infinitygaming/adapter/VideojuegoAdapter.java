package com.example.infinitygaming.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.infinitygaming.ProductDetails;
import com.example.infinitygaming.R;
import com.example.infinitygaming.Videojuego;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VideojuegoAdapter extends RecyclerView.Adapter<VideojuegoAdapter.VideojuegoViewHolder> {

    Context context;
    List<Videojuego> listaVideojuegos;
    List<Videojuego> listaOriginal;



    public VideojuegoAdapter(Context context, List<Videojuego> trendyProductsList) {
        this.context = context;
        this.listaVideojuegos = trendyProductsList;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaVideojuegos);
    }

    public void setFilteredList(List<Videojuego> filteredList){
        this.listaVideojuegos = filteredList;
    }

    @NonNull
    @Override
    public VideojuegoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trendy_items,parent,false);
        return new VideojuegoViewHolder(view);
    }

    public void filtrado(String textoBuscar){
        int longitud = textoBuscar.length();
        if(longitud == 0){
            listaVideojuegos.clear();
            listaVideojuegos.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Videojuego> coleccion = listaVideojuegos.stream()
                        .filter(i -> i.getDescripcion().toLowerCase().contains(textoBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaVideojuegos.clear();
                listaVideojuegos.addAll(coleccion);
            }else{
                for (Videojuego v: listaOriginal) {
                    if(v.getDescripcion().toLowerCase().contains(textoBuscar.toLowerCase())){
                        listaVideojuegos.add(v);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull VideojuegoViewHolder holder, final int position) {
        String aux = listaVideojuegos.get(position).getPrecio() + "€";
        byte[] aux2= listaVideojuegos.get(position).getImagen();
        Glide.with(context).load(aux2).fitCenter().into(holder.trendyImageView);
        holder.price.setText(aux);
        holder.description.setText(listaVideojuegos.get(position).getDescripcion());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ProductDetails.class);
                i.putExtra("image",aux2);
                i.putExtra("price",aux);
                i.putExtra("desc",listaVideojuegos.get(holder.getAdapterPosition()).getDescripcion());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaVideojuegos.size();
    }



    public static class VideojuegoViewHolder extends RecyclerView.ViewHolder{
        ImageView trendyImageView;
        TextView description,price;
        public VideojuegoViewHolder(@NonNull View itemView) {
            super(itemView);


            description = itemView.findViewById(R.id.descripcion);
            price = itemView.findViewById(R.id.precio);
            trendyImageView = itemView.findViewById(R.id.trendyImage);
        }
    }
}
