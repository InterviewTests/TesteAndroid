package com.galdino.testandroid.data.source

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.domain.model.Cell
import io.reactivex.Single

interface DataSource {
    fun getCell(): Single<CellResponseBody>
}