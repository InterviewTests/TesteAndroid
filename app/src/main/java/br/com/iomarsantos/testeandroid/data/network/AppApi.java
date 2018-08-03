package br.com.iomarsantos.testeandroid.data.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.iomarsantos.testeandroid.data.model.CellResponse;
import io.reactivex.Single;

@Singleton
public class AppApi implements Api {

    @Inject
    public AppApi() {
    }

    @Override
    public Single<CellResponse> getCellApiCall() {
        return Rx2AndroidNetworking.get(EndPoint.ENDPOINT_CELLS)
                .build()
                .getObjectSingle(CellResponse.class);
    }
}
