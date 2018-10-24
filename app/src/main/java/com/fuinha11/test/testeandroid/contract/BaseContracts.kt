package com.fuinha11.test.testeandroid.contract

import android.widget.Toast

open class BaseContracts {
    interface View {
        fun showToast(message: String, length: Int = Toast.LENGTH_SHORT)
        fun dismissKeyboard()
        fun showLoading(message: String = "Loading...")
        fun hideLoading()
    }

    interface Presenter {
        fun onError(e: Exception)
    }
}