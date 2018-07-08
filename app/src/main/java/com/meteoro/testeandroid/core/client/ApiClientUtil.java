package com.meteoro.testeandroid.core.client;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientUtil {

    private Builder builder;

    private ApiClientUtil(Builder builder) {
        this.builder = builder;
    }

    public <T> T create(Class<T> type) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        if (builder.enableLogging) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientBuilder.addInterceptor(logging);
        }

        clientBuilder.connectTimeout(builder.connectTimeout, TimeUnit.SECONDS)
                .readTimeout(builder.readTimeout, TimeUnit.SECONDS)
                .writeTimeout(builder.writeTimeout, TimeUnit.SECONDS);

        OkHttpClient client = clientBuilder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(builder.baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(type);
    }

    public static class Builder {

        private String baseUrl = null;
        private boolean enableLogging = false;
        private long readTimeout = 30;
        private long writeTimeout = 30;
        private long connectTimeout = 30;

        public ApiClientUtil build() {
            return new ApiClientUtil(this);
        }

        public Builder url(String url) {
            this.baseUrl = url;
            return this;
        }

        public Builder log(boolean enableLogging) {
            this.enableLogging = enableLogging;
            return this;
        }
    }
}
