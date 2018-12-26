package com.seletiva.santander.investment.contact;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.datasources.cells.CellsDatasource;
import com.seletiva.santander.investment.ui.form.MainForm;
import com.seletiva.santander.investment.ui.form.MainFormPresenter;
import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;

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

public class TestMainFormPresenter {
    @Mock private MainForm.View formView;
    @Mock private CellsDatasource cellsDatasourceMock;
    private MainFormPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainFormPresenter(formView, cellsDatasourceMock, Schedulers.trampoline(),
                Schedulers.trampoline());
    }

    @Test
    public void showAndHideProgress_whenRequestCellDataIsSuccessful() {
        CellHolder holderMock = new CellHolder();

        when(cellsDatasourceMock.getCells()).thenReturn(Observable.just(holderMock));

        presenter.loadCells();

        InOrder inOrder = Mockito.inOrder(formView);
        inOrder.verify(formView, times(1)).showProgressDialog();
        inOrder.verify(formView, times(1)).hideProgressDialog();
    }

    @Test
    public void showErrorMessage_whenRequestCellDataFails() {
        Exception exception = new Exception();

        when(cellsDatasourceMock.getCells()).thenReturn(Observable.<CellHolder>error(exception));

        presenter.loadCells();

        InOrder inOrder = Mockito.inOrder(formView);
        inOrder.verify(formView, times(1)).showProgressDialog();
        inOrder.verify(formView, times(1)).hideProgressDialog();
        inOrder.verify(formView, times(1)).showMessage(R.string.conn_error_cells);
    }
}
