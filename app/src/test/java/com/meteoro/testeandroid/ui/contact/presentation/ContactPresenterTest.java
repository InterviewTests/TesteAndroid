package com.meteoro.testeandroid.ui.contact.presentation;

import com.meteoro.testeandroid.core.lifecycle.AutomaticUnsubscriber;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;
import com.meteoro.testeandroid.ui.contact.presentation.coordinator.GetCellsCoordinator;

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

public class ContactPresenterTest {

    @Mock
    GetCellsCoordinator getCellsCoordinator;

    @Mock
    AutomaticUnsubscriber automaticUnsubscriber;

    ContactContract.Presenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = spy(
                new ContactPresenter(
                        getCellsCoordinator,
                        automaticUnsubscriber)
        );
    }

    @Test
    public void initializeContents_shouldCallCoordinatorsInOrder() {
        when(getCellsCoordinator.call(any()))
                .thenReturn(Observable.just(mock(CellsViewModel.class)));

        InOrder callOrder = inOrder(
                getCellsCoordinator
        );

        presenter.initializeContents();

        callOrder.verify(getCellsCoordinator).call(any());
    }
}
