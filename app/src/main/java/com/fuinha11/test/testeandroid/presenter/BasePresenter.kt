package com.fuinha11.test.testeandroid.presenter

import android.content.Context
import com.fuinha11.test.testeandroid.contract.BaseContracts
import com.fuinha11.test.testeandroid.ui.BaseActivity
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext

@EBean
open class BasePresenter<V: BaseContracts.View> : BaseContracts.Presenter {

    lateinit var view: V
    lateinit var activity: BaseActivity

    @RootContext
    open fun setContext(context: Context) {
        view = context as V
        activity = context as BaseActivity
    }

    override fun onError(e: Exception) {
        view.showToast(e.localizedMessage)
    }

}