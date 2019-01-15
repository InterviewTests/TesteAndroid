package com.luccasmelo.santandereveris.view_model

import androidx.lifecycle.ViewModel
import com.luccasmelo.santandereveris.data.repository.RepositoryAPI

open class BaseViewModel:ViewModel(){
    var api:RepositoryAPI =RepositoryAPI()

}