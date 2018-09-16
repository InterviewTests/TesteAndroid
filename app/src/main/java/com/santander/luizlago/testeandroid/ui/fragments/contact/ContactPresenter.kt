package com.santander.luizlago.testeandroid.ui.fragments.contact

import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.api.models.Cell
import com.santander.luizlago.testeandroid.api.repositories.SantanderRepository
import com.santander.luizlago.testeandroid.enums.Type
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ContactPresenter : ContactContract.Presenter {

    var view: ContactContract.View? = null
    var repository: SantanderRepository? = null

    constructor(view: ContactContract.View) {
        this.view = view
    }

    override fun onCreated() {
        repository = SantanderRepository()
    }

    override fun onResume() {
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
        cells.forEach {
            createCell(it)
        }
    }

    fun createCell(cell: Cell) {
        when(cell.type) {
            Type.FIELD.ordinal -> return
            Type.TEXT.ordinal -> return
            Type.IMAGE.ordinal -> return
            Type.CHECKBOX.ordinal -> return
            Type.SEND.ordinal -> return
        }
    }
}