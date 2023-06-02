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

    @GET(Constants.PRODUCTS_END_POINT + "{categoryName}")
    Call<List<Product>> fetchProducts(@Path("categoryName") String categoryName);

    @GET(Constants.PRODUCT_DETAILS_END_POINT + "{productId}")
    Call<Product> fetchProductsDetails(@Path("productId") int productId);
}
