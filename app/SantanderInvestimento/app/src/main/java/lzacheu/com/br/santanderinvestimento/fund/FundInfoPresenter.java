package lzacheu.com.br.santanderinvestimento.fund;

import android.util.Log;

import lzacheu.com.br.santanderinvestimento.data.FundDataSource;
import lzacheu.com.br.santanderinvestimento.data.FundRepository;
import lzacheu.com.br.santanderinvestimento.model.fund.Screen;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class FundInfoPresenter implements FundInfoContract.Presenter {

    private static final String LOG_TAG = FundInfoPresenter.class.getSimpleName();
    private FundInfoContract.View view;
    private FundRepository fundRepository;

    public FundInfoPresenter(FundRepository fundRepository, FundInfoContract.View view) {
        this.view = view;
        this.fundRepository = fundRepository;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.showLoading();
        fundRepository = new FundRepository();
    }

    @Override
    public void fetchData() {
        fundRepository.getFunds(new FundDataSource.LoadFundCallback() {
            @Override
            public void onFundsLoaded(Screen screen) {
                view.hideLoading();
                view.bindValues(screen);

            }

            @Override
            public void onFailed() {
                Log.e(LOG_TAG, "Ocorreu um problema");
            }
        });
    }
}
