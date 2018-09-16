package com.santander.luizlago.testeandroid.ui.fragments.contact

import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.api.models.Cell
import com.santander.luizlago.testeandroid.api.repositories.SantanderRepository
import com.santander.luizlago.testeandroid.enums.Type
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContactPresenter : ContactContract.Presenter {

    var cells: List<Cell>? = null
    var view: ContactContract.View? = null
    var repository: SantanderRepository? = null

    constructor(view: ContactContract.View) {
        this.view = view
    }

    override fun onCreated() {
        repository = SantanderRepository()
    }

    override fun onResume() {
        if (this.cells?.isNotEmpty() == true) {
            return
        }

        repository!!.getCells()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { this.view?.showLoadingIndication(true) }
                .doOnTerminate { this.view?.showLoadingIndication(false) }
                .subscribe({
                    processResponse(it!!.cells)
                }, {
                    this.view?.showMessageError(R.string.comunication_failed_message)
                }, {
                    this.view?.showLoadingIndication(false)
                })
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }

    fun processResponse(cells: List<Cell>) {
        this.cells = cells
        cells.forEach {
            createCell(it)
        }
    }

    fun createCell(cell: Cell) {
        when(Type.valueOf(cell.type)) {
            Type.FIELD -> this.view?.addCellField(cell)
            Type.TEXT -> this.view?.addCellText(cell)
            Type.IMAGE -> this.view?.addCellImage(cell)
            Type.CHECKBOX -> this.view?.addCellCheckBox(cell)
            Type.SEND -> this.view?.addCellSend(cell)
        }
    }
}