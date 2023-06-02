package com.example.quickbuy.product;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickbuy.databinding.ProductItemBinding;

public class ProductViewHolder extends RecyclerView.ViewHolder {
     ProductItemBinding binding;

    public ProductViewHolder(@NonNull ProductItemBinding productItemBinding) {
        super(productItemBinding.getRoot());
        binding = productItemBinding;
    }
}
