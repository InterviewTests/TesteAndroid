package br.com.rafael.santanderteste.datasource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Classe que retorna uma instancia de RetrofitClient
 */
class RetrofitClient {
    companion object {
        fun getRetrofitClient(): Retrofit {
            var retrofit = Retrofit.Builder()
                .baseUrl("https://floating-mountain-50292.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
    }
}