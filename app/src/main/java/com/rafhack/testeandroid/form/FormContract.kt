package com.rafhack.testeandroid.form

import com.rafhack.testeandroid.data.entities.form.Cell

interface FormContract {

    interface View {
        fun setProgress(active: Boolean)
        fun showErrorMessage(message: String)
        fun inflateCells(cells: ArrayList<Cell>)
    }

    interface UserActionListener {
        fun getCells()
    }
}