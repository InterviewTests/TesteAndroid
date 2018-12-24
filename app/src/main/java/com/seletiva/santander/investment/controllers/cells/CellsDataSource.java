package com.seletiva.santander.investment.controllers.cells;

import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CellsDataSource {
    @GET("cells.json")
    Observable<CellHolder> getCells();
}
