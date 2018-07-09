package com.meteoro.testeandroid.ui.contact.di;

import com.meteoro.testeandroid.core.di.PerFragment;
import com.meteoro.testeandroid.core.di.component.LibraryComponent;
import com.meteoro.testeandroid.ui.contact.presentation.ContactFragment;

import dagger.Component;

@PerFragment
@Component(
        dependencies = {LibraryComponent.class},
        modules = {ContactModule.class}
)
public interface ContactComponent {
    void inject(ContactFragment fragment);
}
