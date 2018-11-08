package com.galdino.testandroid.screens

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.galdino.testandroid.mvp.Contract
import com.galdino.testandroid.screens.base.ToastController

abstract class BaseActivity: AppCompatActivity(), Contract.View {
    private lateinit var mToastController: ToastController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        mToastController = getToastController()
        onInitView()
    }

    open fun getToastController() = ToastController(this)

    abstract fun getLayoutResource(): Int

    abstract fun onInitView()

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun showShortToast(resId: Int) {
        mToastController.showShortToast(resId)
    }

    fun showShortToast(text: String?) {
        mToastController.showToast(text, Toast.LENGTH_SHORT)
    }

    fun showLongToast(resId: Int) {
        mToastController.showShortToast(resId)
    }

    fun showLongToast(text: String?) {
        mToastController.showToast(text, Toast.LENGTH_LONG)
    }

    override fun onDestroy() {
        mToastController.cancel()
        super.onDestroy()
    }
}