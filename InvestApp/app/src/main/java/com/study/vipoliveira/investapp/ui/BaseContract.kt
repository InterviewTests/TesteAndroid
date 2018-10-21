package com.study.vipoliveira.investapp.ui

interface BaseContract {
    interface View {
        fun displayLoadingUI()
        fun hideLoadingUI()
        fun displayError(error: String)
    }

    interface Presenter<in T> {
        fun attach(view: T)
    }
}