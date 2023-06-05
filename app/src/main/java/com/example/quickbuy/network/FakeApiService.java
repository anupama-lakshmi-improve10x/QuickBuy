package com.example.quickbuy.network;

import com.example.quickbuy.Constants;
import com.example.quickbuy.cart.Cart;
import com.example.quickbuy.category.Category;
import com.example.quickbuy.modelclass.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FakeApiService {

    @GET("categories")
    Call<List<Category>> fetchCategories();

    @GET("products/")
    Call<List<Product>> fetchProducts(@Query("categoryId") int categoryId);

    @GET("products/" + "{Id}")
    Call<Product> fetchProductsDetails(@Path("Id") int Id);

    @GET("carts/1?userId=1")
    Call<Cart> fetchCartProduct();

    @GET("products/")
    Call<List<Product>> fetchSearchProducts(@Query("title") String title);
}
