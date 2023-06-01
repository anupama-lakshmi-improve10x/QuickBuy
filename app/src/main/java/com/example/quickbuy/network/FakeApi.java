package com.example.quickbuy.network;

import com.example.quickbuy.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeApi {

    public FakeApiService createFakeApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FakeApiService fakeApiService = retrofit.create(FakeApiService.class);
        return fakeApiService;
    }
}
