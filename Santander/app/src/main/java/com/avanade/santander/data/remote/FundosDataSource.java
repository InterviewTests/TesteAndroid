package com.avanade.santander.data.remote;


import android.support.annotation.NonNull;

import com.avanade.santander.fundos.domain.model.Fundos;

public interface FundosDataSource {

    interface LoadFundosCallback {
        void onFundosLoaded(Fundos fundos);
    }

    void getFundos(@NonNull LoadFundosCallback callback);

}
