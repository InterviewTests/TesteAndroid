package com.study.vipoliveira.investapp.ui

interface BaseContract {
    interface View {
        fun displayLoadingUI()
        fun hideLoadingUI()
    }

    interface Presenter<in T> {
        fun attach(view: T)
    }
}