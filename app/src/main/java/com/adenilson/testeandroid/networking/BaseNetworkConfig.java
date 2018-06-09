package com.adenilson.testeandroid.networking;

import com.adenilson.testeandroid.BuildConfig;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 09/06/2018
 */

public class BaseNetworkConfig {

    private static Retrofit.Builder builder = new Retrofit.Builder();

    public static Retrofit buildRetrofit() {
        OkHttpClient okHttpClient;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        return builder
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .client(okHttpClient)
                .build();
    }
}
