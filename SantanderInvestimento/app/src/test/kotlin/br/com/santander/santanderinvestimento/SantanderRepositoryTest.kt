package br.com.santander.santanderinvestimento

import br.com.santander.santanderinvestimento.api.SantanderApi
import br.com.santander.santanderinvestimento.di.DatasourceProperties.SERVER_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class SantanderRepositoryTest {

    lateinit var api: SantanderApi

    val mockServer = MockWebServer()

    @Before @Throws
    open fun init(){
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        val okHttpClient =  OkHttpClient.Builder()
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor).build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(SERVER_URL)
                .build()

        api = retrofit.create(SantanderApi::class.java)
    }

    @After @Throws fun tearDown() {
        // We're done with tests, shut it down
        mockServer.shutdown()
    }

}
