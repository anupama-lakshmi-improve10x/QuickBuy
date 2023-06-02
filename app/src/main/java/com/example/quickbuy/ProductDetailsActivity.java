package com.example.quickbuy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quickbuy.databinding.ActivityProductBinding;
import com.example.quickbuy.databinding.ActivityProductDetailsBinding;
import com.example.quickbuy.modelclass.Product;
import com.example.quickbuy.network.FakeApi;
import com.example.quickbuy.network.FakeApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseClassActivity {
    private ActivityProductDetailsBinding binding;
    private int productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("ProductsDetails");
        if(getIntent().hasExtra(Constants.KEY_PRODUCT)) {
            productId = getIntent().getIntExtra(Constants.KEY_PRODUCT, productId);
        }
        fetchProductDetails();
    }

    private void fetchProductDetails() {
        showProgressBar();
        Call<Product> call = fakeApiService.fetchProductsDetails(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                hideProgressBar();
                Product product = response.body();
                binding.setProduct(product);
                binding.productDetailsRb.setRating(product.rating.getRate());
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to add Product Details");
            }
        });
    }

    private void showProgressBar() {
        binding.productDetailsProgressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar() {
        binding.productDetailsProgressBar.setVisibility(View.GONE);
    }
}