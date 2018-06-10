package com.adenilson.testeandroid.investiment;

import com.adenilson.testeandroid.networking.webservices.investiment.InvestmentResponse;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public interface OnRequestInvestmentListener {

    void onRequestFundSuccess(InvestmentResponse response);

    void onRequestFundFailed(int messageResourceId);

}
