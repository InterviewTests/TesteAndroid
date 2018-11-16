package com.galdino.testandroid.data.source.remote

import com.galdino.testandroid.data.entity.CellResponseBody
import io.reactivex.Single

interface Api {
    fun getCell(): Single<CellResponseBody>
}