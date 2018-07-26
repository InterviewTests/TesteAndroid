package com.rafhack.testeandroid.form

interface FormContract {

    interface View {
        fun inflateCells()
    }

    interface UserActionListener {
        fun getCells()
    }
}