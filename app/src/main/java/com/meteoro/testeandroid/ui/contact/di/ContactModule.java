package com.meteoro.testeandroid.ui.contact.di;

import android.arch.lifecycle.LifecycleOwner;

import com.meteoro.testeandroid.core.di.PerFragment;
import com.meteoro.testeandroid.core.lifecycle.AutomaticUnsubscriber;
import com.meteoro.testeandroid.core.lifecycle.LifecycleUnsubscriber;
import com.meteoro.testeandroid.ui.contact.domain.interactor.ShowLoadingContact;
import com.meteoro.testeandroid.ui.contact.domain.interactor.ShowLoadingContactImpl;
import com.meteoro.testeandroid.ui.contact.presentation.ContactContract;
import com.meteoro.testeandroid.ui.contact.presentation.ContactFragment;
import com.meteoro.testeandroid.ui.contact.presentation.ContactPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactModule {

    private ContactFragment fragment;

    public ContactModule(ContactFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    ContactContract.View view() {
        return fragment;
    }

    @Provides
    @PerFragment
    ContactContract.Presenter presenter(ContactPresenter presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    LifecycleOwner lifecycleOwner() {
        return fragment;
    }

    @Provides
    @PerFragment
    AutomaticUnsubscriber automaticUnsubscriber(LifecycleUnsubscriber impl) {
        return impl;
    }

    @Provides
    @PerFragment
    ShowLoadingContact showLoadingContact(ShowLoadingContactImpl impl) {
        return impl;
    }
}
