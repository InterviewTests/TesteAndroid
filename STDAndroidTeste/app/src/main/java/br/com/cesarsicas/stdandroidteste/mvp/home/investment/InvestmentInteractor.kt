package br.com.cesarsicas.stdandroidteste.mvp.home.investment

import br.com.cesarsicas.stdandroidteste.api.SantanderApi
import br.com.cesarsicas.stdandroidteste.domains.InvestmentData
import br.com.cesarsicas.stdandroidteste.okhttp.LoggerInterceptor
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by julio on 4/22/18.
 */
class InvestmentInteractor {

    //api json returned is crashing (malformed structure)
    fun getInvestmentData(): Single<InvestmentData> {

        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()

        // add logging as last interceptor
        httpClient.addInterceptor(LoggerInterceptor())

        val retrofit = Retrofit.Builder()
                .baseUrl("https://floating-mountain-50292.herokuapp.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()


        val service = retrofit.create(SantanderApi::class.java)


        return service.getInvestmentData()

    }
}