package br.com.cesarsicas.stdandroidteste.mvp.form

import br.com.cesarsicas.stdandroidteste.api.SantanderApi
import br.com.cesarsicas.stdandroidteste.domains.Cell
import br.com.cesarsicas.stdandroidteste.domains.CellReceiver
import br.com.cesarsicas.stdandroidteste.okhttp.LoggerInterceptor
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by julio on 4/21/18.
 */

class FormInteractor {
    fun getCells(): Single<CellReceiver> {

        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(LoggerInterceptor())  // <-- this is the important line!

        val retrofit = Retrofit.Builder()
                .baseUrl("https://floating-mountain-50292.herokuapp.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()



        val service = retrofit.create(SantanderApi::class.java)


        return service.getCells()

    }
}
