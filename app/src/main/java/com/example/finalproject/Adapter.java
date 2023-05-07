package com.example.finalproject;

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

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Model> data;

    public Adapter(Context context,List<Model> data) {
        this.context=context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
holder.tvCityName.setText(data.get(position).getCityName());
holder.tvCityPrice.setText(data.get(position).getCityPrice());
holder.ivCity.setImageResource(data.get(position).getCityImg());
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent sendIntent=new Intent(context,TourDetailActivity.class);
        sendIntent.putExtra("image",data.get(position).getCityImg());
        sendIntent.putExtra("cityName",data.get(position).getCityName());
        sendIntent.putExtra("cityPrice",data.get(position).getCityPrice());
        sendIntent.putExtra("cityDesc",data.get(position).getCityDesc());
        context.startActivity(sendIntent);
    }
});

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvCityName, tvCityPrice;
        ImageView ivCity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCityName=itemView.findViewById(R.id.tvCityName);
            tvCityPrice=itemView.findViewById(R.id.tvCityPrice);
            ivCity=itemView.findViewById(R.id.ivCity);
        }
    }
}
