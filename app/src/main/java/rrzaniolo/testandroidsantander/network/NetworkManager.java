package rrzaniolo.testandroidsantander.network;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rrzaniolo.testandroidsantander.utils.Constants;
import rrzaniolo.testandroidsantander.utils.Utils;

/**
 * This class is responsible for handling Network connection.
 * */
public class NetworkManager {

    //region --- Variables
    /**
     * Interface containing HTTP methods as given by Retrofit.
     * */
    private static FloatingMountainApi floatingMountainApi;

    /**
     * Static adapter to be used in entire app.
     * */
    private static volatile OkHttpClient.Builder okHttpClient = null;
    private static volatile Retrofit retrofit = null;
    //endregion

    //region --- CONSTRUCTORS ---
    private NetworkManager() {}
    //endregion

    //region --- INSTANCES ---
    /**
     * By Default OkHttp has connection/read/write as 30 seconds timeout
     * @return okHttpClient
     */
    private static OkHttpClient getOkHttpClient(){
        if (okHttpClient == null) {
            synchronized (NetworkManager.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder();

                    okHttpClient.connectTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
                    okHttpClient.writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
                    okHttpClient.readTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);

                    okHttpClient.addInterceptor(new HeaderInterceptor());
                    okHttpClient.addInterceptor(new TimeoutInterceptor());
                }
            }
        }

        return okHttpClient.build();
    }

    /**
     * Singleton for the Retrofit instance.
     * */
    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (NetworkManager.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(FloatingMountainApi.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(getOkHttpClient())
                            .build();
                }
            }
        }

        return retrofit;
    }

    /**
     * Method to retrieve the Floating Mountain Api implementation.
     * */
    public static synchronized FloatingMountainApi getInstance() {
        if (floatingMountainApi == null)
            floatingMountainApi = getRetrofit().create(FloatingMountainApi.class);

        return floatingMountainApi;
    }
    //endregion

    /**
     * Interceptors to customize the Api calls.
     * */
    //region --- Interceptors
    private static class HeaderInterceptor implements Interceptor {

        HeaderInterceptor() { }

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        }
    }

    private static class TimeoutInterceptor implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();

            int connectTimeout = chain.connectTimeoutMillis();
            int readTimeout = chain.readTimeoutMillis();
            int writeTimeout = chain.writeTimeoutMillis();

            String connectNew = request.header(Constants.CONNECT_TIMEOUT_LABEL);
            String readNew = request.header(Constants.READ_TIMEOUT_LABEL);
            String writeNew = request.header(Constants.WRITE_TIMEOUT_LABEL);

            if (Utils.isNotNullNorEmprty(connectNew)) {
                connectTimeout = Integer.valueOf(connectNew);
            }

            if (Utils.isNotNullNorEmprty(readNew)) {
                readTimeout = Integer.valueOf(readNew);
            }

            if (Utils.isNotNullNorEmprty(writeNew)) {
                writeTimeout = Integer.valueOf(writeNew);
            }

            return chain.withConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                    .withReadTimeout(readTimeout, TimeUnit.MILLISECONDS)
                    .withWriteTimeout(writeTimeout, TimeUnit.MILLISECONDS)
                    .proceed(request);
        }
    }
    //endregion
}
