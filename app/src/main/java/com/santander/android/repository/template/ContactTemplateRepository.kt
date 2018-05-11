package com.santander.android.repository.template

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.santander.android.model.template.ContactsTemplate
import com.santander.android.remote.template.contactTemplate.ContactTemplateApiManager
import com.santander.android.util.Resource
import io.reactivex.android.schedulers.AndroidSchedulers

object ContactTemplateRepository {

    private val resource: MutableLiveData<Resource<ContactsTemplate>> = MutableLiveData()

    fun observe(): LiveData<Resource<ContactsTemplate>> {
        updateRepository()
        return resource
    }

    private fun updateRepository() {

        ContactTemplateApiManager.read().observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it != null) resource.value = Resource.success(it)
            else resource.value = Resource.error()
        }

    }

}