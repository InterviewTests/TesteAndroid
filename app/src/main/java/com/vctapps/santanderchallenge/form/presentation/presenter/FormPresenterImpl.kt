package com.vctapps.santanderchallenge.form.presentation.presenter

import com.vctapps.santanderchallenge.form.domain.CellRepository
import com.vctapps.santanderchallenge.form.domain.cell.Cell
import com.vctapps.santanderchallenge.form.presentation.domain.cell.FormViewBuilder
import com.vctapps.santanderchallenge.form.presentation.view.FormView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FormPresenterImpl(private val view: FormView,
                        private val repository: CellRepository): FormPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun onSendEventClicked() {
        if(view.isFormError()){
            view.showFormErrors()
        } else {
            view.showSuccessView()
        }
    }

    override fun onStart() {
        view.showLoading()

        compositeDisposable.add(
            repository.getCells()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({listCell ->
                        proccessResponse(listCell)
                    }, { error ->
                        Timber.e(error)
                        view.showError()
                    })
        )
    }

    private fun proccessResponse(listCell: MutableList<Cell>) {
        val formViewBuilder = FormViewBuilder(view.getFormLayout())

        formViewBuilder.proccessCellsFormView(listCell)
    }

    override fun onPause() {
        compositeDisposable.clear()
    }

}