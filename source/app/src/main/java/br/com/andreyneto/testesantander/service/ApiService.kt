package br.com.andreyneto.testesantander.service

import br.com.andreyneto.testesantander.model.CellResponse
import br.com.andreyneto.testesantander.model.InvestResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class ApiService {
    private val BASE_URL = "https://floating-mountain-50292.herokuapp.com/"

    fun getApi(): SantanderApi {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(SantanderApi::class.java)
    }

    interface SantanderApi {

        @get:GET("fund.json")
        val fund: Call<InvestResponse>

        @get:GET("cells.json")
        val cells: Call<CellResponse>
    }
}