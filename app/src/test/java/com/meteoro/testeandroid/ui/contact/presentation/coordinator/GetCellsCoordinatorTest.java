package com.meteoro.testeandroid.ui.contact.presentation.coordinator;

import com.meteoro.testeandroid.core.data.model.Cells;
import com.meteoro.testeandroid.ui.contact.domain.interactor.ConvertToCellsViewModel;
import com.meteoro.testeandroid.ui.contact.domain.interactor.GetCells;
import com.meteoro.testeandroid.ui.contact.domain.interactor.ShowCellsViewModel;
import com.meteoro.testeandroid.ui.contact.domain.interactor.ShowLoadingContact;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class GetCellsCoordinatorTest {

    @Mock
    ShowLoadingContact showLoadingContact;

    @Mock
    GetCells getCells;

    @Mock
    ConvertToCellsViewModel convertToCellsViewModel;

    @Mock
    ShowCellsViewModel showCellsViewModel;

    GetCellsCoordinator impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new GetCellsCoordinator(
                        showLoadingContact,
                        getCells,
                        convertToCellsViewModel,
                        showCellsViewModel)
        );
    }

    @Test
    public void callGetCellsCoordinator_shouldExecuteInOrder() {
        when(showLoadingContact.call(any()))
                .thenReturn(Observable.just(""));
        when(getCells.call(any()))
                .thenReturn(Observable.just(mock(Cells.class)));
        when(convertToCellsViewModel.call(any()))
                .thenReturn(Observable.just(mock(CellsViewModel.class)));
        when(showCellsViewModel.call(any()))
                .thenReturn(Observable.just(mock(CellsViewModel.class)));

        InOrder callOrder = inOrder(
                showLoadingContact,
                getCells,
                convertToCellsViewModel,
                showCellsViewModel
        );

        Observable.just("")
                .compose(impl)
                .subscribe();

        callOrder.verify(showLoadingContact).call(any());
        callOrder.verify(getCells).call(any());
        callOrder.verify(convertToCellsViewModel).call(any());
        callOrder.verify(showCellsViewModel).call(any());
    }
}
