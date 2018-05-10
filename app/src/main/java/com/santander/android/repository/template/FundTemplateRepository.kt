package com.santander.android.repository.template

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.santander.android.model.template.FundTemplate
import com.santander.android.remote.template.fundTemplate.FundTemplateApiManager
import com.santander.android.util.Resource
import io.reactivex.android.schedulers.AndroidSchedulers

object FundTemplateRepository {

    private val resource: MutableLiveData<Resource<FundTemplate>> = MutableLiveData()

    fun observe(): LiveData<Resource<FundTemplate>> {
        updateRepository()
        return resource
    }

    private fun updateRepository() {

        FundTemplateApiManager.read().observeOn(AndroidSchedulers.mainThread()).subscribe {
            if (it != null) resource.value = Resource.success(it)
            else resource.value = Resource.error()
        }

    }

}