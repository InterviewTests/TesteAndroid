package lucasonofre.santandertest.request

import android.content.Context
import android.util.Log
import lucasonofre.santandertest.R
import lucasonofre.santandertest.model.Cell
import lucasonofre.santandertest.model.Screen
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

class RequestItens(private val context: Context){

    private val service:Service

    init {
        this.service = RetrofitClient.instance(context).create(Service::class.java)
    }

    /**
     * Retorna as cells
     */
    fun getCells(): Call<Cell> {
        val endpoint = context.resources.getString(R.string.url_cels)

        Log.i("Request\n",endpoint)

        return service.getCells(endpoint)
    }

    /**
     * Retorna os fundos
     */

    fun getFund(): Call<lucasonofre.santandertest.model.Screen> {
        val endpoint = context.resources.getString(R.string.url_fund)

        Log.i("Request\n",endpoint)

        return service.getFund(endpoint)
    }

    /**
     * Interface que requisita os parâmetros e faz as chamadas
     */

    private interface Service {

        @GET
        fun getCells(@Url url: String): Call<Cell>

        @GET
        fun getFund(@Url url: String): Call<Screen>

    }

}