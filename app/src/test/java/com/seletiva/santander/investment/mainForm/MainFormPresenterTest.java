package com.seletiva.santander.investment.mainForm;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.controllers.cells.CellsController;
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

public class MainFormPresenterTest {
    @Mock private MainForm.View formView;
    @Mock private CellsController cellsControllerMock;
    private MainFormPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainFormPresenter(formView, cellsControllerMock, Schedulers.trampoline(),
                Schedulers.trampoline());
    }

    @Test
    public void requestViewToBuildForm_whenRequestCellDataIsSuccessful() {
        CellHolder holderMock = new CellHolder();

        when(cellsControllerMock.getCells()).thenReturn(Observable.just(holderMock));

        presenter.loadCells();

        InOrder inOrder = Mockito.inOrder(formView);
        inOrder.verify(formView, times(1)).showProgressDialog();
        inOrder.verify(formView, times(1)).hideProgressDialog();
        inOrder.verify(formView, times(1)).buildCellsUsingHolder(holderMock);
    }

    @Test
    public void showErrorMessage_whenRequestCellDataFails() {
        Exception exception = new Exception();

        when(cellsControllerMock.getCells()).thenReturn(Observable.<CellHolder>error(exception));

        presenter.loadCells();

        InOrder inOrder = Mockito.inOrder(formView);
        inOrder.verify(formView, times(1)).showProgressDialog();
        inOrder.verify(formView, times(1)).hideProgressDialog();
        inOrder.verify(formView, times(1)).showMessage(R.string.conn_error_cells);
    }

}
