package com.galdino.testandroid.domain.interactor.cell

import com.galdino.testandroid.data.entity.CellResponseBody
import io.reactivex.Single

interface IGetCell {
    fun buildUseCaseObservable(params: GetCell.Params): Single<CellResponseBody>
}