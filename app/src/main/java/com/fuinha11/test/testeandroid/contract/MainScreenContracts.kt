package com.fuinha11.test.testeandroid.contract

import com.fuinha11.test.testeandroid.data.model.Investment
import com.fuinha11.test.testeandroid.ui.view.CellHolder

class MainScreenContracts {

    interface View: BaseContracts.View {
        fun showContactFragment()
        fun populateContactFragment(cells: List<CellHolder>)
        fun showCellErrors()
        fun showContactView()
        fun showThankYouView()
        fun showInvestmentFragment()
        fun populateInvestmentFragment(investment: Investment)
    }

    interface Presenter: BaseContracts.Presenter {
        fun getFields()
        fun getInvestment()
        fun sendBtnClick()
        fun fieldsAreValid(cells: List<CellHolder>) : Boolean
    }
}