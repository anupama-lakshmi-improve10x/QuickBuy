package com.example.quickbuy;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.quickbuy.cart.Cart;
import com.example.quickbuy.category.Category;
import com.example.quickbuy.modelclass.Product;
import com.example.quickbuy.network.FakeApi;
import com.example.quickbuy.network.FakeApiService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test

    public void getCategory() throws IOException {
        FakeApiService service = new FakeApi().createFakeApiService();
        Call<List<Category>> call = service.fetchCategories();
        List<Category> categories = call.execute().body();
        assertNotNull(categories);
        assertFalse(categories.isEmpty());
        System.out.println(new Gson().toJson(categories));
    }

    @Test

    public void getCategoryProduct() throws IOException {
        FakeApiService service = new FakeApi().createFakeApiService();
        Call<List<Product>> call = service.fetchProducts(1);
        List<Product> products = call.execute().body();
        assertNotNull(products);
        assertFalse(products.isEmpty());
        System.out.println(new Gson().toJson(products));
    }

    @Test

    public void getProductDetails() throws IOException {
        FakeApiService service = new FakeApi().createFakeApiService();
        Call <Product> call = service.fetchProductsDetails(4);
        Product products = call.execute().body();
        assertNotNull(products);
        System.out.println(new Gson().toJson(products));
    }

    @Test

    public void getCartProducts() throws IOException {
        FakeApiService service = new FakeApi().createFakeApiService();
        Call<Cart> call = service.fetchCartProduct();
        Cart products = call.execute().body();
        assertNotNull(products);
        System.out.println(new Gson().toJson(products));
    }
}