package br.com.santander.desafio.webservice

import br.com.santander.desafio.Constants
import br.com.santander.desafio.webservice.cells.CellsApiRest
import br.com.santander.desafio.webservice.fund.ScreenApiRest
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Enzo Teles on 19,June,2018
 * Barueri - SP
 */
open class BaseApi{

    val serviceCells: CellsApiRest
    val serviceFund: ScreenApiRest

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val gson = GsonBuilder().setLenient().create()

        val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging).build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(Constants.DOMAIN_URL)
                .client(client)
                .build()
        serviceCells = retrofit.create<CellsApiRest>(CellsApiRest::class.java)
        serviceFund = retrofit.create<ScreenApiRest>(ScreenApiRest::class.java)
    }
}