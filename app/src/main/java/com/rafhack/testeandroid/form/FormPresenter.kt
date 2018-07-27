package com.rafhack.testeandroid.form

import com.rafhack.testeandroid.data.domain.FormInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FormPresenter(val view: FormContract.View) : FormContract.UserActionListener {

    var interactor = FormInteractor()

    override fun getCells() {
        view.setProgress(true)
        interactor.getCells()
                .subscribeOn(Schedulers.io())
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