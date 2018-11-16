package com.galdino.testandroid.data.source.remote

import android.content.Context
import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.data.source.DataSource
import com.galdino.testandroid.domain.model.Cell
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class RemoteDataSourceMocked(context: Context): DataSource, BaseMocked(context) {
    override fun getCell(): Single<CellResponseBody> {
        return getSingleFromFile("mockapi/cells.json", CellResponseBody::class.java)
                .delay(1.3.toLong(), TimeUnit.SECONDS)
    }
}