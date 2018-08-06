package br.com.iomarsantos.testeandroid.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.iomarsantos.testeandroid.data.model.CellResponse;
import br.com.iomarsantos.testeandroid.data.model.ScreenResponse;
import br.com.iomarsantos.testeandroid.data.network.Api;
import br.com.iomarsantos.testeandroid.di.ApplicationContext;
import io.reactivex.Single;

@Singleton
public class AppRepository implements Repository {

    private static final String TAG = "AppRepository";

    private final Context mContext;
    private final Api mApi;

    @Inject
    public AppRepository(@ApplicationContext Context context,
                         Api api) {
        mContext = context;
        mApi = api;
    }

    @Override
    public Single<CellResponse> getCellApiCall() {
        return this.mApi.getCellApiCall();
    }

    @Override
    public Single<ScreenResponse> getFundApiCall() {
        return this.mApi.getFundApiCall();
    }

}
