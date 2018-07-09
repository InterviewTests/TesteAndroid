package com.meteoro.testeandroid.ui.investiment.domain.interactor;

import com.meteoro.testeandroid.core.data.model.ScreenVo;
import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;

import rx.Observable;

public interface ConvertScreenToViewModel extends Observable.Transformer<ScreenVo, ScreenViewModel> {
}
