package com.meteoro.testeandroid.ui.contact.domain.interactor;

import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;
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

public class ShowResultValidateImplTest {

    @Mock
    ContactContract.View view;

    ShowResultValidate impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new ShowResultValidateImpl(
                        Schedulers.immediate(),
                        view)
        );
    }

    @Test
    public void callResult_shouldSuccess() {
        CellsViewModel viewModel = new CellsViewModel()
                .isAllValid(true);

        Observable.just(viewModel)
                .compose(impl)
                .subscribe();

        verify(view).successValidate();
        verify(view, never()).showViewModel(any());
    }

    @Test
    public void callResult_shouldViewModel() {
        CellsViewModel viewModel = new CellsViewModel()
                .isAllValid(false);

        Observable.just(viewModel)
                .compose(impl)
                .subscribe();

        verify(view).showViewModel(any());
        verify(view, never()).successValidate();
    }
}
