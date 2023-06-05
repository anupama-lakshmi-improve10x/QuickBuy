package com.example.quickbuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.quickbuy.databinding.ActivitySearchProductsBinding;
import com.example.quickbuy.modelclass.Product;
import com.example.quickbuy.product.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProductsActivity extends BaseActivity {

    ActivitySearchProductsBinding binding;
    private ArrayList<Product> products = new ArrayList<>();
    public ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fetchSearchProducts("Generic");
        setUpSearchAdapter();
        setUpSearchRv();
    }

    private void setUpSearchAdapter() {
        productAdapter = new ProductAdapter();
        productAdapter.setData(products);

    }

    private void setUpSearchRv() {
        binding.searchProductsRv.setLayoutManager(new GridLayoutManager(this, 2));
        binding.searchProductsRv.setAdapter(productAdapter);
    }

    private void fetchSearchProducts(String s) {
        Call<List<Product>> call = fakeApiService.fetchSearchProducts("Generic");
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> productList = response.body();
                productAdapter.setData(productList);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(SearchProductsActivity.this, "failed to load data", Toast.LENGTH_SHORT).show();

            }
        });

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchSearchProducts(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/

}