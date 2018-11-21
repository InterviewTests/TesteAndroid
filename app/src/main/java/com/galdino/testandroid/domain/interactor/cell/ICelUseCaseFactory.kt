package com.galdino.testandroid.domain.interactor.cell

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.domain.interactor.UseCase

interface ICelUseCaseFactory {
    fun loadForm(): UseCase<CellResponseBody, GetCell.Params>
}