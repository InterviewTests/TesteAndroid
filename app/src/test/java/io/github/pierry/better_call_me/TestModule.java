package io.github.pierry.better_call_me;

import android.content.Context;
import dagger.Provides;
import io.github.pierry.better_call_me.di.Modules;
import io.github.pierry.better_call_me.ui.presenters.ContactPresenter;
import io.github.pierry.better_call_me.ui.presenters.contracts.IContactPresenter;
import javax.inject.Singleton;
import org.mockito.Mockito;

public class TestModule extends Modules {

  public TestModule(Context context) {
    super(context);
  }

  @Provides @Singleton public IContactPresenter providesContactPresenter() {
    return Mockito.mock(ContactPresenter.class);
  }

  @Provides public Context context() {
    return Mockito.mock(Context.class);
  }
}
