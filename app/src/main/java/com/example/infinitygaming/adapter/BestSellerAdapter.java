package com.example.infinitygaming.adapter;

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
import com.example.infinitygaming.TrendyProducts;

import java.util.List;

public class BestSellerAdapter extends RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder>{
    Context context;
    List<TrendyProducts> bestSellerList;

    public BestSellerAdapter(Context context, List<TrendyProducts> bestSellerList) {
        this.context = context;
        this.bestSellerList = bestSellerList;
    }

    @NonNull
    @Override
    public BestSellerAdapter.BestSellerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trendy_items,parent,false);
        return new BestSellerAdapter.BestSellerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestSellerAdapter.BestSellerViewHolder holder, final int position) {
        holder.name.setText(bestSellerList.get(position).getName());
        holder.price.setText(bestSellerList.get(position).getPrice());
        holder.description.setText(bestSellerList.get(position).getDescription());
        holder.trendyImageView.setImageResource(bestSellerList.get(position).getImageUrl());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ProductDetails.class);
                i.putExtra("name",bestSellerList.get(holder.getAdapterPosition()).getName());
                i.putExtra("image",bestSellerList.get(holder.getAdapterPosition()).getImageUrl());
                i.putExtra("price",bestSellerList.get(holder.getAdapterPosition()).getPrice());
                i.putExtra("desc",bestSellerList.get(holder.getAdapterPosition()).getDescription());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bestSellerList.size();
    }

    public static class BestSellerViewHolder extends RecyclerView.ViewHolder{
        ImageView trendyImageView;
        TextView name,description,price;
        public BestSellerViewHolder(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.nombre);
            description = itemView.findViewById(R.id.descripcion);
            price = itemView.findViewById(R.id.precio);
            trendyImageView = itemView.findViewById(R.id.trendyImage);
        }
    }
}
