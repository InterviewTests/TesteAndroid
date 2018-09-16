package com.santander.luizlago.testeandroid.commons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.santander.luizlago.testeandroid.R

abstract class BaseFragment<T> : Fragment(), BaseView {

    var presenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.presenter = createPresente()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (this.presenter as BasePresenter).onCreated()
    }

    override fun onResume() {
        super.onResume()
        (this.presenter as BasePresenter).onResume()
    }

    override fun onPause() {
        super.onPause()
        (this.presenter as BasePresenter).onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        (this.presenter as BasePresenter).onResume()
    }

    override fun showMessageError(resId: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this.context!!)
                                            .setTitle(getString(R.string.comunication_failed))
                                            .setMessage(resId)
        builder.create().show()
    }

    abstract fun createPresente() : T
}