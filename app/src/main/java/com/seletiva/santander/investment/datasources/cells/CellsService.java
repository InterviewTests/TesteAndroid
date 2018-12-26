package com.seletiva.santander.investment.datasources.cells;

import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Interface do serviço de `cells`
 */
public interface CellsService {
    @GET("cells.json")
    Observable<CellHolder> getCells();
}
