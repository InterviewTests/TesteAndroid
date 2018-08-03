package br.com.iomarsantos.testeandroid.data.network;

import br.com.iomarsantos.testeandroid.data.model.CellResponse;
import io.reactivex.Single;

public interface Api {
    Single<CellResponse> getCellApiCall();
}
