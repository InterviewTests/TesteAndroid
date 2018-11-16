package com.galdino.testandroid.data.source.remote

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.data.source.DataSource
import io.reactivex.Single

class RemoteDataSource: DataSource {
    override fun getCell(): Single<CellResponseBody> {
        return Single.just(CellResponseBody(listOf()))
    }
}