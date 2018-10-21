package com.study.vipoliveira.investapp.ui.contact

import com.study.vipoliveira.investapp.data.network.contact.entities.ContactFormResponse
import com.study.vipoliveira.investapp.ui.BaseContract
import java.util.ArrayList

interface ContactFormContract {
    interface View: BaseContract.View{
        fun createContactForm(contact: ContactFormResponse)
        fun showEmailError()
        fun showPhoneError()
        fun showTextError()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun getContactForm()
        fun validateInput(views: ArrayList<android.view.View>): Boolean
        fun sendInfo()
    }
}
