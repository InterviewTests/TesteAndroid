package com.santander.android.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.santander.android.model.template.FundTemplate
import com.santander.android.repository.template.FundTemplateRepository
import com.santander.android.util.Resource

class FundViewModel: ViewModel() {

    fun observe(): LiveData<Resource<FundTemplate>> {
        return FundTemplateRepository.observe()
    }

}