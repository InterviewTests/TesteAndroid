package com.galdino.testandroid.screens.base

import android.content.Context
import android.widget.Toast

open class ToastController(private val context: Context) {

    private var mToast: Toast? = null

    fun showShortToast(resId: Int) {
        showShortToast(context.getString(resId))
    }

    fun showShortToast(text: String?) {
        showToast(text, Toast.LENGTH_SHORT)
    }

    fun showLongToast(resId: Int) {
        showShortToast(context.getString(resId))
    }

    fun showLongToast(text: String?) {
        showToast(text, Toast.LENGTH_LONG)
    }

    open fun showToast(text: String?, length: Int) {
        if (text != null) {
            cancel()
            mToast = Toast.makeText(context, text, length)
            mToast?.show()
        }
    }

    open fun cancel() {
        mToast?.cancel()
    }
}