package com.meteoro.testeandroid.ui.investiment.domain.interactor;

import com.meteoro.testeandroid.core.data.model.ScreenVo;

import rx.Observable;

public interface GetFund extends Observable.Transformer<String, ScreenVo> {
}
