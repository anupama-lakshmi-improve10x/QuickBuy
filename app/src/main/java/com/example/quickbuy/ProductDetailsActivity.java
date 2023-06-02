package com.example.quickbuy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.quickbuy.databinding.ActivityProductBinding;
import com.example.quickbuy.databinding.ActivityProductDetailsBinding;
import com.example.quickbuy.modelclass.Product;
import com.example.quickbuy.network.FakeApi;
import com.example.quickbuy.network.FakeApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
    ActivityProductDetailsBinding binding;
    int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(getIntent().hasExtra("products")) {
            productId = getIntent().getIntExtra("products", productId);
        }
        fetchProductDetails();
    }

    private void fetchProductDetails() {
        FakeApi fakeApi = new FakeApi();
        FakeApiService fakeApiService = fakeApi.createFakeApiService();
        Call<Product> call = fakeApiService.fetchProductsDetails(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                Product product = response.body();
                binding.setProduct(product);
                binding.productDetailsRb.setRating(product.rating.getRate());
                Toast.makeText(ProductDetailsActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ProductDetailsActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}