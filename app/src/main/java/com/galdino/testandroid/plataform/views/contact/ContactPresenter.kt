package com.galdino.testandroid.plataform.views.contact

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.domain.Observer
import com.galdino.testandroid.domain.interactor.IUseCaseFactory
import com.galdino.testandroid.domain.interactor.cell.GetCell
import com.galdino.testandroid.domain.interactor.UseCaseFactory
import com.galdino.testandroid.mvp.BasePresenter


class ContactPresenter(private val useCaseFactory: IUseCaseFactory): BasePresenter<ContactContract.View>(), ContactContract.Presenter {
    override fun loadForm() {
        val loadForm = useCaseFactory.loadForm()
        loadForm.execute(object: Observer<CellResponseBody>(){
            override fun onSuccess(t: CellResponseBody) {
                mView?.onLoadFormSuccess(t.cells)
            }
        }, GetCell.Params())
    }

}