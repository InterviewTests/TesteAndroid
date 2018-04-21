package com.vctapps.santanderchallenge.form.data

import com.vctapps.santanderchallenge.core.data.FloatingMountainApi
import com.vctapps.santanderchallenge.form.domain.CellRepository
import com.vctapps.santanderchallenge.form.domain.cell.Cell
import io.reactivex.Flowable

class CellRepositoryImpl(private val floatingMountainApi: FloatingMountainApi): CellRepository {

    override fun getCells(): Flowable<MutableList<Cell>> {
        return floatingMountainApi.getCells()
                .flatMap { response ->
                    if(response.isSuccessful){
                        Flowable.just(response.body())
                    } else {
                        Flowable.empty()
                    }
                }
                .map(CellMapper::map)
    }
}