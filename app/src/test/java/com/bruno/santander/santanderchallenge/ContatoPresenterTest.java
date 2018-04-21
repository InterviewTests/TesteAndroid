package com.bruno.santander.santanderchallenge;

import com.bruno.santander.santanderchallenge.contato.presentation.ContatoContract;
import com.bruno.santander.santanderchallenge.contato.presentation.ContatoPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ContatoPresenterTest {

    @Mock
    private ContatoContract.View view;

    private ContatoContract.Presenter presenter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        presenter = new ContatoPresenter(view);
    }

    @Test
    public void testGetCells(){
        presenter.getCells();
    }

    @Test
    public void testSaveContato(){
        presenter.saveContato();
    }

}
