package com.example.funretrofit.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRetro {
    private static Retrofit getRetrofit(){
        Retrofit r = new Retrofit.Builder()
                .baseUrl("https://script.googleusercontent.com/macros/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return r;
    }
    public static BaseApiService getApiService(){
        return getRetrofit().create(BaseApiService.class);
    }
}
