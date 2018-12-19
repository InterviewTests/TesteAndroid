package com.avanade.santander.data;

import android.support.annotation.NonNull;

import com.avanade.santander.fundos.domain.model.Fundos;

import static com.google.common.base.Preconditions.checkNotNull;

public class FundosRepository implements FundosDataSource {

    private static FundosRepository INSTANCE = null;

    private final FundosDataSource mFundosRemoteDataSource;

    /**
     * Não utilizaremos o SQLite para armazenar valores fundos, pois, ele é volátil
     * Também podemos nos ater a uma NoSQL caso necessário
     */
    // private final FundosDataSource mFundosLocalDataSource;


    /**
     * Singleton
     */
    private FundosRepository(@NonNull FundosDataSource fundosRemoteDataSource) {
        mFundosRemoteDataSource = checkNotNull(fundosRemoteDataSource);
    }

    public static FundosRepository getInstance(FundosDataSource fundosRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new FundosRepository(fundosRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getFundos(@NonNull final LoadFundosCallback callback) {
        checkNotNull(callback);
        mFundosRemoteDataSource.getFundos(new LoadFundosCallback() {
            @Override
            public void onFundosLoaded(Fundos fundos) {
                callback.onFundosLoaded(fundos);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }


}
