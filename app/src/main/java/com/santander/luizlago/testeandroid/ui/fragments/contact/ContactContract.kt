package com.santander.luizlago.testeandroid.ui.fragments.contact

import com.santander.luizlago.testeandroid.api.models.Cell
import com.santander.luizlago.testeandroid.commons.BasePresenter
import com.santander.luizlago.testeandroid.commons.BaseView

interface ContactContract {

    interface View : BaseView {
        fun showLoadingIndication(isShow: Boolean)
        fun addCellField(cell: Cell)
        fun addCellText(cell: Cell)
        fun addCellImage(cell: Cell)
        fun addCellCheckBox(cell: Cell)
        fun addCellSend(cell: Cell)
    }

    interface Presenter : BasePresenter {

    }

}