package com.seletiva.santander.investment.controllers.cells;

import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;
import com.seletiva.santander.investment.services.ApiClient;

import io.reactivex.Observable;


public class CellsController {
    private CellsDataSource dataSource;

    public CellsController() {
        dataSource = ApiClient.getInstance().create(CellsDataSource.class);
    }

    public Observable<CellHolder> getCells() {
        return dataSource.getCells();
    }
}
