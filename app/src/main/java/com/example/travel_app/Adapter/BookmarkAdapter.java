package com.example.travel_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travel_app.Activity.DetailActivity;
import com.example.travel_app.Activity.TicketActivity;
import com.example.travel_app.Domain.ItemDomain;
import com.example.travel_app.databinding.ViewholderCartBinding;

import java.util.ArrayList;


public class  BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {
    ArrayList<ItemDomain> items;
    Context context;
    ViewholderCartBinding binding;

    public BookmarkAdapter(ArrayList<ItemDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BookmarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        binding = ViewholderCartBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.ViewHolder holder, int position) {
        binding.titleTxt.setText(items.get(position).getTitle());
        binding.priceTxt.setText("$"+items.get(position).getPrice());
        binding.addressTxt.setText(items.get(position).getAddress());
        binding.scoreTxt.setText(""+items.get(position).getScore());

        Glide.with(context)
                .load(items.get(position).getPic())
                .into(binding.pic);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, TicketActivity.class);
            intent.putExtra("object", items.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Phương thức mới để lấy ItemDomain từ vị trí
    public ItemDomain getItem(int position) {
        return items.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ViewholderCartBinding binding) {
            super(binding.getRoot());
        }
    }
}

