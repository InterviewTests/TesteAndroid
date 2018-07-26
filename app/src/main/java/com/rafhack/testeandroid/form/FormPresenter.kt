package com.rafhack.testeandroid.form

import com.rafhack.testeandroid.data.domain.FormInteractor
import io.reactivex.android.schedulers.AndroidSchedulers

class FormPresenter(val view: FormContract.View) : FormContract.UserActionListener {

    private val interactor = FormInteractor()

    override fun getCells() {
        view.setProgress(true)
        interactor.getCells()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.setProgress(false)
                    view.inflateCells(it)
                }, {
                    view.setProgress(false)
                    view.showErrorMessage(it?.message!!)
                })
    }
}