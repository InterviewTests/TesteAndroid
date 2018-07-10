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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ShowCellsViewModelImplTest {

    @Mock
    ContactContract.View view;

    ShowCellsViewModel impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new ShowCellsViewModelImpl(
                        Schedulers.immediate(),
                        view)
        );
    }

    @Test
    public void success_shuldShouwViewModel() {
        Observable.just(mock(CellsViewModel.class))
                .compose(impl)
                .subscribe();

        verify(view).showViewModel(any());
        verify(view, never()).showLoading();
        verify(view, never()).showError();
    }
}
