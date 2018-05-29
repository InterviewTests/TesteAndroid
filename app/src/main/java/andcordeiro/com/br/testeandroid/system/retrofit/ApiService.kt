package andcordeiro.com.br.testeandroid.system.retrofit

import andcordeiro.com.br.testeandroid.entities.ResultContact
import andcordeiro.com.br.testeandroid.entities.ResultScreen
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("cells.json")
    fun getCells(): Call<ResultContact>

    @GET("fund.json")
    fun getScreen(): Call<ResultScreen>
}