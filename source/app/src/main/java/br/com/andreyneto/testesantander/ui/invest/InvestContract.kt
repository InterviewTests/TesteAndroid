package br.com.andreyneto.testesantander.ui.invest

import br.com.andreyneto.testesantander.model.Screen
import br.com.andreyneto.testesantander.ui.base.BasePresenter
import br.com.andreyneto.testesantander.ui.base.BaseView

class InvestContract {

    interface View: BaseView<Presenter> {
        fun showData(model: Screen)
    }

    interface Presenter: BasePresenter {
        fun getData()
    }
}