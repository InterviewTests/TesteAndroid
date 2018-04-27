package io.github.pierry.better_call_me.interactors;

import android.content.Context;
import io.github.pierry.better_call_me.App;
import io.github.pierry.better_call_me.api.contracts.ISyncApi;
import io.github.pierry.better_call_me.common.support.HttpStatusCode;
import io.github.pierry.better_call_me.common.support.RxBus;
import io.github.pierry.better_call_me.domain.viewmodels.FundBase;
import io.github.pierry.better_call_me.interactors.contracts.IInvestmentInteractor;
import io.reactivex.observers.DisposableObserver;
import javax.inject.Inject;
import retrofit2.Response;
import timber.log.Timber;

public class InvestmentInteractor implements IInvestmentInteractor {

  @Inject ISyncApi syncApi;
  @Inject RxBus rxBus;

  public InvestmentInteractor(Context context) {
    App.getControllerComponent(context).inject(this);
  }

  @Override public void fetchFund() {
    syncApi.fetchFund().subscribe(new DisposableObserver<Response<FundBase>>() {
      @Override public void onNext(Response<FundBase> response) {
        switch (response.code()) {
          case HttpStatusCode.OK:
            handle(response.body());
            break;
        }
      }

      @Override public void onError(Throwable e) {
        Timber.e(e);
      }

      @Override public void onComplete() {
      }
    });
  }

  private void handle(FundBase fund) {
    if (fund.getScreen() == null) {
      return;
    }
    rxBus.send(fund.getScreen());
  }
}
