package com.avanade.santander.contato.presentation;

import com.avanade.santander.UseCaseHandler;
import com.avanade.santander.contato.domain.model.Cell;
import com.avanade.santander.contato.domain.model.Formulario;
import com.avanade.santander.contato.domain.usecase.GetFormulario;
import com.avanade.santander.data.CellsDataSource;
import com.avanade.santander.data.CellsRepository;
import com.avanade.santander.data.local.CellsLocalDataSource;
import com.avanade.santander.data.remote.CellsRemoteDataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ContatoPresenterTest {

    Formulario FORMULARIO;

    @Mock
    ContatoContract.IView mContatoView;

    @Mock
    UseCaseHandler mUseCaseHandler;

    @Mock
    GetFormulario mGetFormulario;

    @Mock
    CellsRemoteDataSource cellsRemoteDataSource;
    @Mock
    CellsLocalDataSource cellsLocalDataSource;

    CellsRepository mCellsRepository;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<CellsDataSource.LoadCellsCallback> mLoadCellsCallbackCaptor;


    @InjectMocks
    ContatoPresenter contatoPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mCellsRepository = CellsRepository.getInstance(cellsRemoteDataSource, cellsLocalDataSource);

        // Inicializa Formulario
        List<Cell> cells = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Cell cell = new Cell(i, i, i, "" + i, "", true, i, i, false);
            cells.add(cell);
        }
        contatoPresenter.FORMULARIO = new Formulario(cells);
    }

    @Test
    public void setPresenterNoView() {
        // Then the presenter is set to the view
        verify(mContatoView).setPresenter(contatoPresenter);
    }

    @Test
    public void testRefreshFormulario() throws Exception {
        when(mContatoView.isActive()).thenReturn(true);

        assertTrue (contatoPresenter.FORMULARIO.getCells().size() > 0);
        contatoPresenter.refreshFormulario();
        verify(mContatoView).desenhaTela(contatoPresenter.FORMULARIO);
    }

    @Test
    public void testGetFormulario() throws Exception {
        when(mContatoView.isActive()).thenReturn(true);

        contatoPresenter.getFormulario();
        verify(mGetFormulario).getRequestValues();
      ;


        // Callback is captured and invoked with stubbed formularios
//        verify(mCellsRepository).getFormulario(mLoadCellsCallbackCaptor.capture());
//        mLoadCellsCallbackCaptor.getValue().onCellsLoaded(FORMULARIO);

        //verify(mUseCaseHandler).execute(mGetFormulario.);


        InOrder inOrder = inOrder(mContatoView);
        inOrder.verify(mContatoView).setLoadingIndicator(true);     // Then progress indicator is shown
        inOrder.verify(mContatoView).setLoadingIndicator(false);    // Then progress indicator is hidden and all formularios are shown in UI

        ArgumentCaptor<Formulario> argumentCaptor = ArgumentCaptor.forClass(Formulario.class);
        verify(mContatoView).desenhaTela(argumentCaptor.capture());
        assertTrue(argumentCaptor.getValue().getCells().size() == 3);

        assertTrue(FORMULARIO.getCells().size() > 0);
        
        
    }

    @Test // OK
    public void testValidaContato() throws Exception {
        List<String> result = contatoPresenter.validaContato("nome", "email@valido.com", "(11) 1111-1111");
        Assert.assertEquals(0, result.size());
    }

    @Test // OK
    public void testEnviarContato() throws Exception {
        contatoPresenter.enviarContato("nome", "email@valid.com", "(11) 1111-1111");
        verify(mContatoView).mostrarTelaEnviada();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme