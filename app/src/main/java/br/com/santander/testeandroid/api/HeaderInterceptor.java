package br.com.santander.testeandroid.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
                .header("Content-Type", "application/json")
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
