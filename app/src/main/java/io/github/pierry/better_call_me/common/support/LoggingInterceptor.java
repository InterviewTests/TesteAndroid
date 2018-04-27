package io.github.pierry.better_call_me.common.support;

import android.support.annotation.NonNull;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class LoggingInterceptor implements Interceptor {

  @Override public Response intercept(@NonNull Chain chain) throws IOException {
    Request request = chain.request();

    long t1 = System.nanoTime();
    Timber.i("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers());
    Response response = chain.proceed(request);

    long t2 = System.nanoTime();
    double time = (t2 - t1) / 1e6d;
    Timber.i("Received response for %s in %.1fms%n%s", response.request().url(), time, response.headers());

    return response;
  }
}
