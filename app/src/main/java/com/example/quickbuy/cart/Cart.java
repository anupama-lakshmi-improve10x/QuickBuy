package com.example.quickbuy.cart;

import java.util.ArrayList;

public class Cart {

    private Integer id;
    private Integer userId;
    private String date;

    public ArrayList<CartProduct> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<CartProduct> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<CartProduct> products) {
        this.products = products;
    }
}
