package com.example.infinitygaming.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinitygaming.ProductDetails;
import com.example.infinitygaming.R;
import com.example.infinitygaming.Tienda;
import com.example.infinitygaming.TrendyProducts;

import java.util.ArrayList;
import java.util.List;

public class TrendyProductsAdapter extends RecyclerView.Adapter<TrendyProductsAdapter.TrendyProductsViewHolder> {

    Context context;
    List<TrendyProducts> trendyProductsList;

    public TrendyProductsAdapter(Context context, List<TrendyProducts> trendyProductsList) {
        this.context = context;
        this.trendyProductsList = trendyProductsList;
    }

    @NonNull
    @Override
    public TrendyProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trendy_items,parent,false);
        return new TrendyProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendyProductsViewHolder holder, final int position) {
        holder.name.setText(trendyProductsList.get(position).getName());
        holder.price.setText(trendyProductsList.get(position).getPrice());
        holder.description.setText(trendyProductsList.get(position).getDescription());
        holder.trendyImageView.setImageResource(trendyProductsList.get(position).getImageUrl());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ProductDetails.class);
                i.putExtra("name",trendyProductsList.get(holder.getAdapterPosition()).getName());
                i.putExtra("image",trendyProductsList.get(holder.getAdapterPosition()).getImageUrl());
                i.putExtra("price",trendyProductsList.get(holder.getAdapterPosition()).getPrice());
                i.putExtra("desc",trendyProductsList.get(holder.getAdapterPosition()).getDescription());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trendyProductsList.size();
    }

    public static class TrendyProductsViewHolder extends RecyclerView.ViewHolder{
        ImageView trendyImageView;
        TextView name,description,price;
        public TrendyProductsViewHolder(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.nombre);
            description = itemView.findViewById(R.id.descripcion);
            price = itemView.findViewById(R.id.precio);
            trendyImageView = itemView.findViewById(R.id.trendyImage);
        }
    }
}
