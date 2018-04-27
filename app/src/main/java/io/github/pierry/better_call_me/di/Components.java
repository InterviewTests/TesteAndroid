package io.github.pierry.better_call_me.di;

import android.content.Context;
import dagger.Component;
import io.github.pierry.better_call_me.api.SyncApi;
import io.github.pierry.better_call_me.interactors.ContactInteractor;
import io.github.pierry.better_call_me.interactors.InvestmentInteractor;
import io.github.pierry.better_call_me.ui.HomeActivity;
import io.github.pierry.better_call_me.ui.elements.EditBox;
import io.github.pierry.better_call_me.ui.fragments.ContactFragment;
import io.github.pierry.better_call_me.ui.fragments.FinishFragment;
import io.github.pierry.better_call_me.ui.fragments.InvestmentFragment;
import io.github.pierry.better_call_me.ui.presenters.ContactPresenter;
import io.github.pierry.better_call_me.ui.presenters.InvestmentPresenter;
import javax.inject.Singleton;

@Singleton @Component(modules = Modules.class) public interface Components {

  Context context();

  void inject(SyncApi syncApi);

  void inject(HomeActivity homeActivity);

  void inject(InvestmentInteractor investmentInteractor);

  void inject(InvestmentPresenter investmentPresenter);

  void inject(ContactInteractor contactInteractor);

  void inject(InvestmentFragment investmentFragment);

  void inject(ContactFragment contactFragment);

  void inject(ContactPresenter contactPresenter);

  void inject(EditBox editBox);

  void inject(FinishFragment finishFragment);
}
