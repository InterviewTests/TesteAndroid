package com.luccasmelo.santandereveris.view_model

import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.luccasmelo.santandereveris.R
import com.luccasmelo.santandereveris.data.model.ContactForm

class ContactViewModel(val navController: NavController): BaseViewModel(){


    val contactForm = MutableLiveData<ContactForm>()
    init {
        api.getContactForm {contactFormResponse ->
            this.contactForm.postValue(contactFormResponse)
        }
    }

    fun sendClick() {
       navController.navigate(R.id.messageFragment)
    }
}