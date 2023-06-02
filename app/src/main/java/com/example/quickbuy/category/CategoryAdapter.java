package com.example.quickbuy.category;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickbuy.Category;
import com.example.quickbuy.databinding.CategoryItemBinding;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<String> categories;
    OnItemActionListener onItemActionListener;


    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    void setData(List<String> category){
        this.categories = category;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryItemBinding categoryItemBinding = CategoryItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(categoryItemBinding);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String category = categories.get(position);
        holder.binding.setCategory(new Category(category));
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemActionListener.onClicked(categories.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
