package lucasonofre.santandertest.request

import android.content.Context
import lucasonofre.santandertest.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{

    /**
     * Singleton que cria uma instancia do retrofit em toda aplicação
     */
    fun instance(context: Context): Retrofit {

        val baseUrl     = context.resources.getString(R.string.base_url)
        val retrofit     = Retrofit.Builder()
                                    .baseUrl(baseUrl)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build()
        return retrofit
    }
}
