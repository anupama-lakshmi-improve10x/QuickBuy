package com.example.quickbuy.product;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickbuy.category.CategoryViewHolder;
import com.example.quickbuy.category.OnItemActionListener;
import com.example.quickbuy.databinding.CategoryItemBinding;
import com.example.quickbuy.databinding.ProductItemBinding;
import com.example.quickbuy.modelclass.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private List<Product> products;

    public void setData(List<Product> productList) {
        this.products = productList;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding productItemBinding = ProductItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        ProductViewHolder productViewHolder = new ProductViewHolder(productItemBinding);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
       Product product = products.get(position);
       holder.binding.setProduct(product);
       holder.binding.ratingRb.setRating(product.rating.getRate());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
