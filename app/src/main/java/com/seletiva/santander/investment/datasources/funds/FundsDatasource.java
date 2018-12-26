package com.seletiva.santander.investment.datasources.funds;

import com.seletiva.santander.investment.services.ApiClient;
import com.seletiva.santander.investment.ui.investments.domain.Funds;

import io.reactivex.Observable;

/**
 * DataSource utilizado para aquisiçao de dados de investimentos
 */
public class FundsDatasource {
    private FundsService dataSource;

    public FundsDatasource() {
        dataSource = ApiClient.getInstance().create(FundsService.class);
    }

    public Observable<Funds> getFunds() {
        return dataSource.getFunds();
    }
}