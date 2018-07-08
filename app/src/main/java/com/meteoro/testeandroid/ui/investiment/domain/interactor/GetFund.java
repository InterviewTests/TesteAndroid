package com.meteoro.testeandroid.ui.investiment.domain.interactor;

import com.meteoro.testeandroid.core.data.model.Screen;

import rx.Observable;

public interface GetFund extends Observable.Transformer<String, Screen> {
}
