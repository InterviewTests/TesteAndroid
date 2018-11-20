package com.galdino.testandroid.plataform.views.contact

import android.content.Context
import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.domain.Observer
import com.galdino.testandroid.domain.interactor.IUseCaseFactory
import com.galdino.testandroid.domain.interactor.cell.GetCell
import com.galdino.testandroid.domain.model.Cell
import com.galdino.testandroid.mvp.BasePresenter


class ContactPresenter(private val useCaseFactory: IUseCaseFactory): BasePresenter<ContactContract.View>(), ContactContract.Presenter {
    override fun loadForm() {
        val loadForm = useCaseFactory.loadForm()
        loadForm.execute(object: Observer<CellResponseBody>(){
            override fun onSuccess(t: CellResponseBody) {
                mView?.onLoadFormSuccess(t.cells)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
            }
        }, GetCell.Params())
    }

    override fun onSendClicked(cells: List<Cell>, context: Context?)
    {
        val cellsWithAnswer: MutableList<Cell> = mutableListOf()
        for(cell in cells) {
            if(!cell.isValid(context)) {
                return
            }
            if(cell.hidden) {
                when(cell.type) {
                    Cell.Type.FIELD->cellsWithAnswer.add(cell)
                    Cell.Type.CHECK_BOX->cellsWithAnswer.add(cell)
                }
            }
        }
    }
}