package com.aline.teste.Rede;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit = null;

    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://floating-mountain-50292.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Service getService(){
        return  this.retrofit.create(Service.class);
    }
}
