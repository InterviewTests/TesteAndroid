package com.seletiva.santander.investment.investments;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.datasources.funds.FundsDatasource;
import com.seletiva.santander.investment.ui.investments.Investments;
import com.seletiva.santander.investment.ui.investments.InvestmentsPresenter;
import com.seletiva.santander.investment.ui.investments.domain.Funds;
import com.seletiva.santander.investment.ui.investments.domain.Screen;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class TestInvestmentPresenter {
    @Mock private Investments.View view;
    @Mock private FundsDatasource datasourceMock;
    private InvestmentsPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new InvestmentsPresenter(view, datasourceMock, Schedulers.trampoline(),
                Schedulers.trampoline());
    }

    @Test
    public void showAndHideProgress_whenRequestFundsDataIsSuccessful() {
        Funds fundsMock = new Funds();
        Screen screenMock = new Screen();

        fundsMock.setScreen(screenMock);
        when(datasourceMock.getFunds()).thenReturn(Observable.just(fundsMock));
        presenter.loadFunds();

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).showProgressDialog();
        inOrder.verify(view, times(1)).hideProgressDialog();

        inOrder.verify(view, times(1)).configureHeader(null,null, null, null);
        inOrder.verify(view, times(1)).configureRiskBar(null, 0);
        inOrder.verify(view, times(1)).configureInfo(null, null);
    }

    @Test
    public void showErrorMessage_whenRequestCellDataFails() {
        Exception exception = new Exception();

        when(datasourceMock.getFunds()).thenReturn(Observable.<Funds>error(exception));

        presenter.loadFunds();

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).showProgressDialog();
        inOrder.verify(view, times(1)).hideProgressDialog();
        inOrder.verify(view, times(1)).showMessage(R.string.conn_error_no_funds);
    }
}
