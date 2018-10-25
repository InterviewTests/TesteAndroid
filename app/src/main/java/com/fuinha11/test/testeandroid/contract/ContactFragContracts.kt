package com.fuinha11.test.testeandroid.contract

import com.fuinha11.test.testeandroid.ui.view.CellHolder


class ContactFragContracts {

    interface Fragment {
        fun populateContactFragment(cells: List<CellHolder>)
        fun showContactView()
        fun showThankYouView()
        fun showCellErrors()
    }

    interface Parent {
        fun onSendBtnClick()
    }
}