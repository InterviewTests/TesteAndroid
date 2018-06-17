package lzacheu.com.br.santanderinvestimento.data;

import lzacheu.com.br.santanderinvestimento.model.fund.Screen;

/**
 * Created by luiszacheu on 6/17/18.
 */

public interface FundDataSource {

    interface LoadFundCallback {

        void onFundsLoaded(Screen screen);

        void onFailed();
    }

    void getFunds(LoadFundCallback callback);
}
