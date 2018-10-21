package com.study.vipoliveira.investapp.ui.contact

import com.study.vipoliveira.investapp.data.network.investment.entities.Screen
import com.study.vipoliveira.investapp.ui.BaseContract

interface ContactFormContract {
    interface View: BaseContract.View{
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun getContactForm()
    }
}
