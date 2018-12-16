package com.avanade.santander.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://floating-mountain-50292.herokuapp.com";

    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseURL) {

        //Gson g = new GsonBuilder().registerTypeAdapter(Cell.class, new CEPDeserializer()).create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
//                    .addConverterFactory(GsonConverterFactory.create(g))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
