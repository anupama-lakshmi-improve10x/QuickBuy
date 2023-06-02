package com.example.quickbuy.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

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

public class ProductActivity extends AppCompatActivity {

    private ArrayList<Product> products = new ArrayList<>();
    private ActivityProductBinding binding;
    private ProductAdapter productAdapter;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (getIntent().hasExtra("category")) {
            category = getIntent().getStringExtra("category");
        }
        fetchProductCategory();
        setUpProductAdapter();
        setUpProductRv();


    }


    private void fetchProductCategory() {
        FakeApi fakeApi = new FakeApi();
        FakeApiService fakeApiService = fakeApi.createFakeApiService();
        Call<List<Product>> call = fakeApiService.fetchProducts(category);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productsList = response.body();
                productAdapter.setData(productsList);
                Toast.makeText(ProductActivity.this, "Successfully updated products", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(ProductActivity.this, "Failed to update products", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setUpProductAdapter() {
        productAdapter = new ProductAdapter();
        productAdapter.setData(products);
        productAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onClicked(int productsId) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
                intent.putExtra("products", productsId);
                startActivity(intent);
            }
        });
    }

    private void setUpProductRv() {
        binding.productsRv.setLayoutManager(new GridLayoutManager(this,2));
        binding.productsRv.setAdapter(productAdapter);
    }
}