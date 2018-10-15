package com.rafhack.testeandroid.form

import com.rafhack.testeandroid.base.BaseContract
import com.rafhack.testeandroid.data.entities.form.Cell

interface FormContract {

    interface View : BaseContract.View {
        fun setProgress(active: Boolean)
        fun showErrorMessage(message: String)
        fun inflateCells(cells: ArrayList<Cell>)
    }

    interface Presenter : BaseContract.Presenter<FormContract.View> {
        fun getCells()
    }
}