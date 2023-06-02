package com.example.quickbuy.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.quickbuy.BaseClassActivity;
import com.example.quickbuy.Constants;
import com.example.quickbuy.ProductDetailsActivity;
import com.example.quickbuy.R;
import com.example.quickbuy.category.CategoryActivity;
import com.example.quickbuy.category.CategoryAdapter;
import com.example.quickbuy.databinding.ActivityProductBinding;
import com.example.quickbuy.modelclass.Product;
import com.example.quickbuy.network.FakeApi;
import com.example.quickbuy.network.FakeApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends BaseClassActivity {

    private ArrayList<Product> products = new ArrayList<>();
    private ActivityProductBinding binding;
    private ProductAdapter productAdapter;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Products");
        if (getIntent().hasExtra(Constants.KEY_CATEGORY)) {
            category = getIntent().getStringExtra(Constants.KEY_CATEGORY);
        }
        setUpProductAdapter();
        setUpProductRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchProductCategory();
    }

    private void fetchProductCategory() {
        showProgressBar();
        Call<List<Product>> call = fakeApiService.fetchProducts(category);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                hideProgressBar();
                List<Product> productsList = response.body();
                productAdapter.setData(productsList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to add product data");
            }
        });
    }

    private void setUpProductAdapter() {
        productAdapter = new ProductAdapter();
        productAdapter.setData(products);
        productAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onClicked(int productId) {
                Intent intent = new Intent(ProductActivity.this, ProductDetailsActivity.class);
                intent.putExtra(Constants.KEY_PRODUCT, productId);
                startActivity(intent);
            }
        });
    }

    private void showProgressBar() {
        binding.progressBarPb.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar() {
        binding.progressBarPb.setVisibility(View.GONE);
    }

    private void setUpProductRv() {
        binding.productsRv.setLayoutManager(new GridLayoutManager(this,2));
        binding.productsRv.setAdapter(productAdapter);
    }
}