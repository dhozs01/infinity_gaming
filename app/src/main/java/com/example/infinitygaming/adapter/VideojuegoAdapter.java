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

import java.util.List;

public class VideojuegoAdapter extends RecyclerView.Adapter<VideojuegoAdapter.VideojuegoViewHolder> {

    Context context;
    List<Videojuego> listaVideojuegos;



    public VideojuegoAdapter(Context context, List<Videojuego> trendyProductsList) {
        this.context = context;
        this.listaVideojuegos = trendyProductsList;
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
