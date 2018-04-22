package br.com.cesarsicas.stdandroidteste.mvp.form

import br.com.cesarsicas.stdandroidteste.api.SantanderApi
import br.com.cesarsicas.stdandroidteste.domains.Cell
import io.reactivex.Single
import retrofit2.Retrofit



/**
 * Created by julio on 4/21/18.
 */

class FormInteractor {
    fun getCells(): Single<List<Cell>> {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://floating-mountain-50292.herokuapp.com/")
                .build()

        val service = retrofit.create(SantanderApi::class.java)

        return service.getCells()

    }
}
