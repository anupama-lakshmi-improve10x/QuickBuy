package com.example.quickbuy.category;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickbuy.databinding.CategoryItemBinding;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    CategoryItemBinding binding;

    public CategoryViewHolder(@NonNull CategoryItemBinding categoryItemBinding) {
        super(categoryItemBinding.getRoot());
        binding = categoryItemBinding;
    }
}
