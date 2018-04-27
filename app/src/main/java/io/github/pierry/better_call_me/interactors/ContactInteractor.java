package io.github.pierry.better_call_me.interactors;

import android.content.Context;
import io.github.pierry.better_call_me.App;
import io.github.pierry.better_call_me.api.contracts.ISyncApi;
import io.github.pierry.better_call_me.common.support.HttpStatusCode;
import io.github.pierry.better_call_me.common.support.RxBus;
import io.github.pierry.better_call_me.domain.viewmodels.CellList;
import io.github.pierry.better_call_me.interactors.contracts.IContactInteractor;
import io.reactivex.observers.DisposableObserver;
import javax.inject.Inject;
import retrofit2.Response;
import timber.log.Timber;

public class ContactInteractor implements IContactInteractor {

  @Inject ISyncApi syncApi;
  @Inject RxBus rxBus;

  public ContactInteractor(Context context) {
    App.getControllerComponent(context).inject(this);
  }

  @Override public void fetchCells() {
    syncApi.fetchCells().subscribe(new DisposableObserver<Response<CellList>>() {
      @Override public void onNext(Response<CellList> response) {
        switch (response.code()) {
          case HttpStatusCode.OK:
            process(response.body());
            break;
        }
      }

      @Override public void onError(Throwable e) {
        Timber.e(e);
      }

      @Override public void onComplete() {
        dispose();
      }
    });
  }

  void process(CellList cells) {
    if (cells.getCells().isEmpty()) {
      return;
    }
    rxBus.send(cells.getCells());
  }
}
