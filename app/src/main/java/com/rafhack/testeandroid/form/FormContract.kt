package com.rafhack.testeandroid.form

import com.rafhack.testeandroid.data.entities.Cell

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