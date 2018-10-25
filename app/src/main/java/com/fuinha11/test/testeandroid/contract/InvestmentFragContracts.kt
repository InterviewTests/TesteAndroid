package com.fuinha11.test.testeandroid.contract

import com.fuinha11.test.testeandroid.data.model.Investment

class InvestmentFragContracts {

    interface Fragment {
        fun populateInvestmentFragment(investment: Investment)
    }

    interface Parent {
        fun onInvestBtnClick()
    }
}