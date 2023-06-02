package com.example.quickbuy.network;

import com.example.quickbuy.Constants;
import com.example.quickbuy.modelclass.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FakeApiService {

    @GET(Constants.CATEGORY_END_POINT)
    Call<List<String>> fetchCategories();

    @GET("/products/category/{categoryName}")
    Call<List<Product>> fetchProducts(@Path("categoryName") String categoryName);

    @GET("products/{productsId}")
    Call<Product> fetchProductsDetails(@Path("productsId") int productId);
}
