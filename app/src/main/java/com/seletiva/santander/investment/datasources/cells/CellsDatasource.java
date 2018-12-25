package com.seletiva.santander.investment.datasources.cells;

import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;
import com.seletiva.santander.investment.services.ApiClient;

import io.reactivex.Observable;


public class CellsDatasource {
    private CellsService dataSource;

    public CellsDatasource() {
        dataSource = ApiClient.getInstance().create(CellsService.class);
    }

    public Observable<CellHolder> getCells() {
        return dataSource.getCells();
    }
}
