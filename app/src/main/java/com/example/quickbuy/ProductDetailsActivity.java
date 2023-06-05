package com.example.quickbuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.quickbuy.cart.CartProductActivity;
import com.example.quickbuy.databinding.ActivityProductDetailsBinding;
import com.example.quickbuy.modelclass.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends BaseActivity {
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
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to add Product Details");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_icon);
        menuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.cart_icon) {
            Intent intent = new Intent(this, CartProductActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showProgressBar() {
        binding.productDetailsProgressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar() {
        binding.productDetailsProgressBar.setVisibility(View.GONE);
    }
}