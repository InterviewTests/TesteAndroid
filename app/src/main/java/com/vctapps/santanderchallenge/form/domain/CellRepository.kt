package com.vctapps.santanderchallenge.form.domain

import com.vctapps.santanderchallenge.form.domain.cell.Cell
import io.reactivex.Flowable

interface CellRepository {

    fun getCells(): Flowable<MutableList<Cell>>

}