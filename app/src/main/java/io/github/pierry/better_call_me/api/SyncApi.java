package io.github.pierry.better_call_me.api;

import android.content.Context;
import io.github.pierry.better_call_me.App;
import io.github.pierry.better_call_me.api.contracts.ISyncApi;
import io.github.pierry.better_call_me.domain.viewmodels.CellList;
import io.github.pierry.better_call_me.domain.viewmodels.FundBase;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import retrofit2.Response;

public class SyncApi implements ISyncApi {

  @Inject IApi api;

  public SyncApi(Context context) {
    App.getControllerComponent(context).inject(this);
  }

  @Override public Observable<Response<CellList>> fetchCells() {
    return api.fetchCells().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io());
  }

  @Override public Observable<Response<FundBase>> fetchFund() {
    return api.fetchFund().observeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
  }
}
