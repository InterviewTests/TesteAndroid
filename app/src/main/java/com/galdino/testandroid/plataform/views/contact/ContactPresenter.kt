package com.galdino.testandroid.plataform.views.contact

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.domain.Observer
import com.galdino.testandroid.domain.interactor.GetCell
import com.galdino.testandroid.domain.interactor.UseCaseFactory
import com.galdino.testandroid.mvp.BasePresenter


class ContactPresenter(private val useCaseFactory: UseCaseFactory): BasePresenter<ContactContract.View>(), ContactContract.Presenter {
    override fun loadForm() {
        val loadForm = useCaseFactory.loadForm()
        loadForm.execute(CellObservable(), GetCell.Params())
    }

    private inner class CellObservable: Observer<CellResponseBody>() {
        override fun onSuccess(t: CellResponseBody) {
            mView?.onLoadFormSuccess(t.cells)
        }
    }
}