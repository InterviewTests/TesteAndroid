package com.galdino.testandroid.data.source

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.domain.IRepository
import io.reactivex.Single

class RepositoryImpl(private val dataSource: DataSource): IRepository {
    override fun getCell(): Single<CellResponseBody> {
        return dataSource.getCell()
    }
}