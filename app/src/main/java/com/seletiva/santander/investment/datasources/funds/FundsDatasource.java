package com.seletiva.santander.investment.datasources.funds;

import com.seletiva.santander.investment.services.ApiClient;

public class FundsDatasource {
    private FundsService dataSource;

    public FundsDatasource() {
        dataSource = ApiClient.getInstance().create(FundsService.class);
    }

    public void getFunds() {}
}