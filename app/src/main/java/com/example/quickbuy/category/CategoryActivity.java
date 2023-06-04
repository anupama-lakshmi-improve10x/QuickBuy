package com.example.quickbuy.category;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.quickbuy.BaseActivity;
import com.example.quickbuy.Constants;
import com.example.quickbuy.R;
import com.example.quickbuy.cart.CartProductActivity;
import com.example.quickbuy.databinding.ActivityCategoryBinding;
import com.example.quickbuy.product.ProductActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends BaseActivity {
    private ActivityCategoryBinding binding;
    private ArrayList<Category> categoryList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Category");
        fetchCategories();
        setUpCategoryAdapter();
        setUpCategoryRv();
    }

    private void fetchCategories() {
        showProgressBar();
        Call<List<Category>> call = fakeApiService.fetchCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
               hideProgressBar();
               List<Category> categories = response.body();
                categoryAdapter.setData(categories);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "Failed to add data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpCategoryRv() {
        binding.categoryRv.setLayoutManager(new LinearLayoutManager(this));
        binding.categoryRv.setAdapter(categoryAdapter);
    }

    private void setUpCategoryAdapter() {
        categoryAdapter = new CategoryAdapter();
        categoryAdapter.setData(categoryList);
        categoryAdapter.setOnItemActionListener(new OnItemActionListener() {

            @Override
            public void onClicked(Integer categoryId) {
                Intent intent = new Intent(CategoryActivity.this, ProductActivity.class);
                intent.putExtra(Constants.KEY_CATEGORY, categoryId);
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
        if (item.getItemId() == R.id.cart_icon) {
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
}