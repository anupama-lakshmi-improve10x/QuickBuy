package com.example.quickbuy.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.quickbuy.BaseActivity;
import com.example.quickbuy.R;
import com.example.quickbuy.databinding.ActivityCartProductBinding;
import com.example.quickbuy.modelclass.Product;
import com.example.quickbuy.product.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartProductActivity extends BaseActivity {

    private ActivityCartProductBinding binding;
    private ArrayList<CartProduct> carts = new ArrayList<>();
    private CartProductAdapter cartProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpCartProductAdapter();
        setUpCartProductRv();
        fetchCartProducts();
    }

    private void fetchCartProducts() {
        Call<Cart> call = fakeApiService.fetchCartProduct();
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if(response.isSuccessful()) {
                    Cart cart = response.body();
                    cartProductAdapter.setData(cart.products);
                }
            }
            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(CartProductActivity.this, "unable to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpCartProductAdapter() {
       cartProductAdapter = new CartProductAdapter();
       cartProductAdapter.setData(carts);
    }

    private void setUpCartProductRv() {
        binding.cartRv.setLayoutManager(new LinearLayoutManager(this));
        binding.cartRv.setAdapter(cartProductAdapter);
    }
}