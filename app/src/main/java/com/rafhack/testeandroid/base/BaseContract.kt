package com.rafhack.testeandroid.base

class BaseContract {

    interface View

    interface Presenter<T : BaseContract.View> {
        fun attach(view: T)
    }

}