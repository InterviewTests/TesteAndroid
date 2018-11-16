package com.galdino.testandroid.domain

import com.galdino.testandroid.data.entity.CellResponseBody
import io.reactivex.Single

interface IRepository {
    fun getCell(): Single<CellResponseBody>
}