package com.example.quickbuy.product;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.quickbuy.BaseActivity;
import com.example.quickbuy.Constants;
import com.example.quickbuy.ProductDetailsActivity;
import com.example.quickbuy.R;
import com.example.quickbuy.cart.CartProductActivity;
import com.example.quickbuy.category.CategoryActivity;
import com.example.quickbuy.databinding.ActivityProductBinding;
import com.example.quickbuy.modelclass.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends BaseActivity {

    private ArrayList<Product> products = new ArrayList<>();
    private ActivityProductBinding binding;
    private ProductAdapter productAdapter;
    int categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Products");
        if (getIntent().hasExtra(Constants.KEY_CATEGORY)) {
            categoryId = getIntent().getIntExtra(Constants.KEY_CATEGORY,0);
           //category = getIntent().getStringExtra(Constants.KEY_CATEGORY);
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
        Call<List<Product>> call = fakeApiService.fetchProducts(categoryId);
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
                showToast("Failed to load product data");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.cart_icon){
            Intent intent = new Intent(this, CartProductActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
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