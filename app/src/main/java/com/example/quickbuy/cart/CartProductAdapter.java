package com.example.quickbuy.cart;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickbuy.databinding.CartProductItemBinding;

import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<CartProduct> cartProducts;

    void setData(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartProductItemBinding cartProductItemBinding = CartProductItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        CartViewHolder cartViewHolder = new CartViewHolder(cartProductItemBinding);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartProduct cartProduct = cartProducts.get(position);
        holder.binding.setCartProduct(cartProduct);
    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }
}
