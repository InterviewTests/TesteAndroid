package com.meteoro.testeandroid.ui.investiment.presentation;

import com.meteoro.testeandroid.core.lifecycle.AutomaticUnsubscriber;
import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;
import com.meteoro.testeandroid.ui.investiment.presentation.coordinator.GetFundCoordinator;

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

public class InvestimentPresenterTest {

    @Mock
    GetFundCoordinator getFundCoordinator;

    @Mock
    AutomaticUnsubscriber automaticUnsubscriber;

    InvestimentContract.Presenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = spy(
                new InvestimentPresenter(
                        getFundCoordinator,
                        automaticUnsubscriber)
        );
    }

    @Test
    public void initializeContents_shouldCallCoordinatorsInOrder() {
        when(getFundCoordinator.call(any()))
                .thenReturn(Observable.just(mock(ScreenViewModel.class)));

        InOrder callOrder = inOrder(
                getFundCoordinator
        );

        presenter.initializeContents();

        callOrder.verify(getFundCoordinator).call(any());
    }
}
