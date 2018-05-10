package com.santander.android.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.santander.android.model.template.ContactsTemplate
import com.santander.android.repository.template.ContactTemplateRepository
import com.santander.android.util.Resource

class ContactViewModel: ViewModel() {

    fun observe(): LiveData<Resource<ContactsTemplate>> {
        return ContactTemplateRepository.observe()
    }

}