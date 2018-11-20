package com.galdino.testandroid.data.source.remote

import com.galdino.testandroid.BuildConfig
import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.data.source.DataSource
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataSource(private val mMockedApi: Api): DataSource
{
    private var mApi : Api

    init {
        val retrofit = getRetrofit()
        mApi = retrofit.create<Api>(Api::class.java)
    }

    private fun getRetrofit(): Retrofit {
        val client: OkHttpClient = OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    //                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()


        return Retrofit.Builder()
                .baseUrl(BuildConfig.URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }

    override fun getCell(): Single<CellResponseBody> {
        return mApi.getCell()
    }
}