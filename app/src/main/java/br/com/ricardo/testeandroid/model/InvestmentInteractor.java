package br.com.ricardo.testeandroid.model;

import retrofit2.Call;

public interface InvestmentInteractor {

    Call<FundApp> requestFundInfos();
}
