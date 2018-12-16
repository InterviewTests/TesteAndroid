package com.avanade.santander.data.local.repository;

import android.support.annotation.NonNull;
import com.avanade.santander.data.remote.FundosDataSource;
import com.avanade.santander.fundos.domain.model.Fundos;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class FundosRepository implements FundosDataSource {

    private static FundosRepository INSTANCE = null;

    private final FundosDataSource mFundosRemoteDataSource;

    /**
     * Singleton
     */
    private FundosRepository(@NonNull FundosDataSource tasksRemoteDataSource) {
        mFundosRemoteDataSource = checkNotNull(tasksRemoteDataSource);
    }

    public static FundosRepository getInstance(FundosDataSource tasksRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new FundosRepository(tasksRemoteDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force to create a new instance next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void getFundos(@NonNull final LoadFundosCallback callback) {
        checkNotNull(callback);
        mFundosRemoteDataSource.getFundos(new LoadFundosCallback() {
            @Override
            public void onFundosLoaded(Fundos fundos) {
                callback.onFundosLoaded(fundos);
            }

        });
    }


}
