package com.galdino.testandroid.screens.form

import com.galdino.testandroid.mvp.BasePresenter

class FormPresenter(private val mInteractor: FormContract.Interactor): BasePresenter<FormContract.View>(), FormContract.Presenter {
}