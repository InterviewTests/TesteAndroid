package io.github.pierry.better_call_me.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import io.github.pierry.better_call_me.api.IApi;
import io.github.pierry.better_call_me.api.SyncApi;
import io.github.pierry.better_call_me.api.contracts.ISyncApi;
import io.github.pierry.better_call_me.common.ContactHelper;
import io.github.pierry.better_call_me.common.support.LayoutHelper;
import io.github.pierry.better_call_me.common.support.RxBus;
import io.github.pierry.better_call_me.common.support.TypefaceHelper;
import io.github.pierry.better_call_me.interactors.ContactInteractor;
import io.github.pierry.better_call_me.interactors.InvestmentInteractor;
import io.github.pierry.better_call_me.interactors.contracts.IContactInteractor;
import io.github.pierry.better_call_me.interactors.contracts.IInvestmentInteractor;
import io.github.pierry.better_call_me.ui.adapters.InfoAdapter;
import io.github.pierry.better_call_me.ui.fragments.ContactFragment;
import io.github.pierry.better_call_me.ui.fragments.FinishFragment;
import io.github.pierry.better_call_me.ui.fragments.InvestmentFragment;
import io.github.pierry.better_call_me.ui.presenters.ContactPresenter;
import io.github.pierry.better_call_me.ui.presenters.InvestmentPresenter;
import io.github.pierry.better_call_me.ui.presenters.contracts.IContactPresenter;
import io.github.pierry.better_call_me.ui.presenters.contracts.IInvestmentPresenter;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Singleton;

@Module public class Modules {

  private final Context context;

  public Modules(Context context) {
    this.context = context;
  }

  @Provides @Singleton public IApi providesApi() {
    return IApi.retrofit.create(IApi.class);
  }

  @Provides @Singleton public ISyncApi providesSyncApi() {
    return new SyncApi(context);
  }

  @Provides @Singleton public IInvestmentPresenter providesHomePresenter() {
    return new InvestmentPresenter(context);
  }

  @Provides @Singleton public IContactPresenter providesContactPresenter() {
    return new ContactPresenter(context);
  }

  @Provides @Singleton public IInvestmentInteractor providesHomeInteractor() {
    return new InvestmentInteractor(context);
  }

  @Provides @Singleton public IContactInteractor providesContactInteractor() {
    return new ContactInteractor(context);
  }

  @Provides @Singleton public RxBus providesRxBus() {
    return new RxBus();
  }

  @Provides @Singleton public CompositeDisposable providesCompositeDisposable() {
    return new CompositeDisposable();
  }

  @Provides @Singleton public InvestmentFragment providesInvestmentFragment() {
    return new InvestmentFragment();
  }

  @Provides @Singleton public ContactFragment providesContactFragment() {
    return new ContactFragment();
  }

  @Provides @Singleton public FinishFragment providesFinishFragment() {
    return new FinishFragment();
  }

  @Provides @Reusable public TypefaceHelper providesTypefaceHelper() {
    return new TypefaceHelper();
  }

  @Provides @Reusable public LayoutHelper providesLayoutHelper() {
    return new LayoutHelper();
  }

  @Provides @Reusable public ContactHelper providesContactHelper() {
    return new ContactHelper();
  }

  @Provides @Singleton public InfoAdapter providesInfoAdapter(){
    return new InfoAdapter();
  }

  @Provides public Context context() {
    return context;
  }
}
