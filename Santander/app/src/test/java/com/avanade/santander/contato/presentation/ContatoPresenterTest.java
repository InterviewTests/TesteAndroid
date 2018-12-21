package com.avanade.santander.contato.presentation;

import com.avanade.santander.UseCase;
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

    @Mock
    UseCaseHandler mUuseCaseHandler;

    @Mock
    ContatoContract.IView mContatoView;

    @Mock
    GetFormulario mGetFormulario;

    @InjectMocks
    ContatoPresenter contatoPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

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
    public void testStart() throws Exception {
        when(mContatoView.isActive()).thenReturn(true);
        contatoPresenter.start();
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
        assertNotNull(contatoPresenter.FORMULARIO);
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