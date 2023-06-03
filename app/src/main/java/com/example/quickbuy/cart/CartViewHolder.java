package com.example.quickbuy.cart;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickbuy.databinding.CartProductItemBinding;

public class CartViewHolder extends RecyclerView.ViewHolder {
    CartProductItemBinding binding;
    public CartViewHolder(@NonNull CartProductItemBinding cartProductItemBinding) {
        super(cartProductItemBinding.getRoot());
        binding = cartProductItemBinding;
    }
}
