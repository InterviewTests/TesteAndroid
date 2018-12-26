package com.seletiva.santander.investment.datasources.cells;

import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;
import com.seletiva.santander.investment.services.ApiClient;

import io.reactivex.Observable;

/**
 * DataSource utilizado para aquisiçao de dados do formulario de contato.
 */
public class CellsDatasource {
    private CellsService dataSource;

    public CellsDatasource() {
        dataSource = ApiClient.getInstance().create(CellsService.class);
    }

    public Observable<CellHolder> getCells() {
        return dataSource.getCells();
    }
}
