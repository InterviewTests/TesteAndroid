package io.github.pierry.better_call_me;

import dagger.Component;
import io.github.pierry.better_call_me.di.Components;
import io.github.pierry.better_call_me.di.Modules;
import javax.inject.Singleton;

@Singleton @Component(modules = Modules.class) public interface TestComponent extends Components {

  void inject(ContactTest contactTest);
}
