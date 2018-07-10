package com.meteoro.testeandroid.ui.contact.domain.interactor;

import com.meteoro.testeandroid.ui.contact.presentation.ContactContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ShowLoadingContactImplTest {

    @Mock
    ContactContract.View view;

    ShowLoadingContact impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new ShowLoadingContactImpl(
                        Schedulers.immediate(),
                        view)
        );
    }

    @Test
    public void callLoading_shouldViewLoading() {
        Observable.just("")
                .compose(impl)
                .subscribe();

        verify(view).showLoading();
        verify(view, never()).showError();
        verify(view, never()).showViewModel(any());
    }
}
