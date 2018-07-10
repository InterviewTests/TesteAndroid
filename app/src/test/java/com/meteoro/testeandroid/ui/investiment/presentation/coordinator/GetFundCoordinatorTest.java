package com.meteoro.testeandroid.ui.investiment.presentation.coordinator;

import com.meteoro.testeandroid.core.data.model.ScreenVo;
import com.meteoro.testeandroid.ui.investiment.domain.interactor.ConvertScreenToViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.interactor.GetFund;
import com.meteoro.testeandroid.ui.investiment.domain.interactor.ShowLoadingInvestiment;
import com.meteoro.testeandroid.ui.investiment.domain.interactor.ShowScreenViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;

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

public class GetFundCoordinatorTest {

    @Mock
    ShowLoadingInvestiment showLoadingInvestiment;

    @Mock
    GetFund getFund;

    @Mock
    ConvertScreenToViewModel convertScreenToViewModel;

    @Mock
    ShowScreenViewModel showScreenViewModel;

    GetFundCoordinator impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new GetFundCoordinator(
                        showLoadingInvestiment,
                        getFund,
                        convertScreenToViewModel,
                        showScreenViewModel)
        );
    }

    @Test
    public void callGetFundCoordinator_shouldExecuteInOrder() {
        when(showLoadingInvestiment.call(any()))
                .thenReturn(Observable.just(""));
        when(getFund.call(any()))
                .thenReturn(Observable.just(mock(ScreenVo.class)));
        when(convertScreenToViewModel.call(any()))
                .thenReturn(Observable.just(mock(ScreenViewModel.class)));
        when(showScreenViewModel.call(any()))
                .thenReturn(Observable.just(mock(ScreenViewModel.class)));

        InOrder callOrder = inOrder(
                showLoadingInvestiment,
                getFund,
                convertScreenToViewModel,
                showScreenViewModel
        );

        Observable.just("")
                .compose(impl)
                .subscribe();

        callOrder.verify(showLoadingInvestiment).call(any());
        callOrder.verify(getFund).call(any());
        callOrder.verify(convertScreenToViewModel).call(any());
        callOrder.verify(showScreenViewModel).call(any());
    }
}
