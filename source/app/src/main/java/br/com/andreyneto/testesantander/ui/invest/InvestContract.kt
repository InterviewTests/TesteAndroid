package br.com.andreyneto.testesantander.ui.invest

import br.com.andreyneto.testesantander.ui.base.BaseView

class InvestContract {

    interface View: BaseView<Presenter> {
        fun showData()
    }

    interface Presenter {
        fun getData()
    }
}