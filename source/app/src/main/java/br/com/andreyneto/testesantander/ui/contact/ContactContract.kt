package br.com.andreyneto.testesantander.ui.contact

import android.content.Context
import br.com.andreyneto.testesantander.model.Cell
import br.com.andreyneto.testesantander.model.Info
import br.com.andreyneto.testesantander.model.MoreInfo
import br.com.andreyneto.testesantander.model.Screen
import br.com.andreyneto.testesantander.ui.base.BasePresenter
import br.com.andreyneto.testesantander.ui.base.BaseView

class ContactContract {

    interface View: BaseView<Presenter> {
        fun showCells(cells: List<Cell>)
        fun showForm()
        fun formSended()
    }

    interface Presenter: BasePresenter {
        fun getCells()
        fun sendForm()
        fun newMessage()
    }
}