package com.seletiva.santander.investment.ui.investments;

import android.support.annotation.NonNull;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.datasources.funds.FundsDatasource;
import com.seletiva.santander.investment.ui.investments.adapters.InvestmentsAdapter;
import com.seletiva.santander.investment.ui.investments.domain.Funds;
import com.seletiva.santander.investment.ui.investments.domain.Screen;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

public class InvestmentsPresenter implements Investments.Presenter {
    private FundsDatasource datasource;
    private Investments.View view;

    @NonNull private Scheduler backgroundScheduler;
    @NonNull private Scheduler mainScheduler;

    public InvestmentsPresenter(@NonNull Investments.View view,
                             @NonNull FundsDatasource datasource,
                             @NonNull Scheduler backgroundScheduler,
                             @NonNull Scheduler mainScheduler) {
        this.view = view;
        this.datasource = datasource;
        this.backgroundScheduler = backgroundScheduler;
        this.mainScheduler = mainScheduler;
    }

    @Override
    public void start() {}

    @Override
    public void loadFunds() {
        view.showProgressDialog();

        datasource.getFunds()
                .subscribeOn(backgroundScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<Funds>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Funds funds) {
                        view.hideProgressDialog();

                        // Paranoia check
                        if(funds != null) {
                            if (funds.getScreen() != null) {
                                configureInvestmentsViewUsingScreen(funds.getScreen());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressDialog();
                        view.showMessage(R.string.conn_error_no_funds);
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    private void configureInvestmentsViewUsingScreen(Screen screen) {
        InvestmentsAdapter adapter = new InvestmentsAdapter(screen.getAllInfoItems());

        view.configureHeader(screen.getTitle(), screen.getFundName(), screen.getWhatIs(), screen.getDefinition());
        view.configureRiskBar(screen.getRiskTitle(), screen.getRisk());
        view.configureInfo(screen.getInfoTitle(), screen.getMoreInfo());
        view.configureInvestmentsList(adapter);
    }
}
