package com.meteoro.testeandroid.ui.investiment.domain.interactor;

import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;
import com.meteoro.testeandroid.ui.investiment.presentation.InvestimentContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ShowScreenViewModelImplTest {

    @Mock
    InvestimentContract.View view;

    ShowScreenViewModel impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new ShowScreenViewModelImpl(
                        Schedulers.immediate(),
                        view)
        );
    }

    @Test
    public void success_shouldShowViewModel() {
        Observable.just(mock(ScreenViewModel.class))
                .compose(impl)
                .subscribe();

        verify(view).showViewModel(any());
        verify(view, never()).showLoading();
        verify(view, never()).showError();
    }
}
