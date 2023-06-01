package com.example.quickbuy.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.quickbuy.databinding.ActivityCategoryBinding;
import com.example.quickbuy.network.FakeApi;
import com.example.quickbuy.network.FakeApiService;
import com.example.quickbuy.product.ProductActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {

    private ActivityCategoryBinding binding;
    private ArrayList<String> categoryList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // setUpData();
        setUpCategoryAdapter();
        setUpCategoryRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchCategories();
    }

    private void fetchCategories() {
        FakeApi fakeApi = new FakeApi();
        FakeApiService fakeApiService = fakeApi.createFakeApiService();
        Call<List<String>> call = fakeApiService.fetchCategories();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Toast.makeText(CategoryActivity.this, "Sucessfully added data", Toast.LENGTH_SHORT).show();
                List<String> categories = response.body();
                categoryAdapter.setData(categories);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
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
            public void onClicked(String categoryName) {
                Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
                intent.putExtra("category", categoryName);
                startActivity(intent);
            }
        });
    }


    /*private void setUpData() {
        categoryList = new ArrayList<>();
        categoryList.add("Electronics");
        categoryList.add("Jewellery");
        categoryList.add("Women's clothing");
    }*/
}