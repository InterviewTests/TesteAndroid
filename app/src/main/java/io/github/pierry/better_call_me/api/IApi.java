package io.github.pierry.better_call_me.api;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.pierry.better_call_me.common.ApiBase;
import io.github.pierry.better_call_me.common.support.LoggingInterceptor;
import io.github.pierry.better_call_me.domain.viewmodels.CellList;
import io.github.pierry.better_call_me.domain.viewmodels.FundBase;
import io.reactivex.Observable;
import java.lang.reflect.Modifier;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface IApi {

  GsonBuilder builder = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
  Gson gson = builder.create();

  OkHttpClient client =
      new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor()).addNetworkInterceptor(new StethoInterceptor()).build();

  Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiBase.URL)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create(gson))
      .client(client)
      .build();

  @GET(ApiBase.CELLS) Observable<Response<CellList>> fetchCells();

  @GET(ApiBase.FUND) Observable<Response<FundBase>> fetchFund();
}

