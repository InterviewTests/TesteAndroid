package br.com.iomarsantos.testeandroid.data.network;

import br.com.iomarsantos.testeandroid.data.model.CellResponse;
import br.com.iomarsantos.testeandroid.data.model.ScreenResponse;
import io.reactivex.Single;

public interface Api {
    Single<CellResponse> getCellApiCall();
    Single<ScreenResponse> getFundApiCall();
}
