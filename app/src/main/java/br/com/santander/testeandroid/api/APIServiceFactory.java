package br.com.santander.testeandroid.api;

import java.util.concurrent.TimeUnit;

import br.com.santander.testeandroid.utils.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIServiceFactory {
    private static Api api;

    public static synchronized Api getInstance() {
        if (api == null)
            api = getRetrofit().create(Api.class);
        return api;
    }

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new HeaderInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
