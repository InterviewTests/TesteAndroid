package com.meteoro.testeandroid.ui.contact.presentation;

import com.meteoro.testeandroid.core.lifecycle.AutomaticUnsubscriber;

import javax.inject.Inject;

public class ContactPresenter implements ContactContract.Presenter {

    private AutomaticUnsubscriber automaticUnsubscriber;

    @Inject
    public ContactPresenter(AutomaticUnsubscriber automaticUnsubscriber) {
        this.automaticUnsubscriber = automaticUnsubscriber;
    }
}
