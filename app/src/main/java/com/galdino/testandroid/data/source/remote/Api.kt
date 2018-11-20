package com.galdino.testandroid.data.source.remote

import com.galdino.testandroid.data.entity.CellResponseBody
import io.reactivex.Single
import retrofit2.http.POST

interface Api {
    @POST("cells.json")
    fun getCell(): Single<CellResponseBody>
}