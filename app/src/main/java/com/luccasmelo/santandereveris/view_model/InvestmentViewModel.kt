package com.luccasmelo.santandereveris.view_model

import androidx.lifecycle.MutableLiveData
import com.luccasmelo.santandereveris.data.model.InvestmentInformation

class InvestmentViewModel: BaseViewModel(){
    val investmentInformation = MutableLiveData<InvestmentInformation>()

    init {
        api.getInvestmentInformation {investmentInformationResponse ->
            this.investmentInformation.postValue(investmentInformationResponse)
        }
    }

}