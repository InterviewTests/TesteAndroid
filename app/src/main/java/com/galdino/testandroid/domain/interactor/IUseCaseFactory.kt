package com.galdino.testandroid.domain.interactor

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.domain.interactor.cell.GetCell

interface IUseCaseFactory {
    fun loadForm(): UseCase<CellResponseBody, GetCell.Params>
}