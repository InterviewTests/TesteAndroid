package com.adenilson.testeandroid.base;

import com.adenilson.testeandroid.networking.BaseNetworkConfig;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 09/06/2018
 */


public class BaseInteractorImpl {

    public <S> S createApi(Class<S> apiClass){
        return BaseNetworkConfig.buildRetrofit().create(apiClass);
    }
}
