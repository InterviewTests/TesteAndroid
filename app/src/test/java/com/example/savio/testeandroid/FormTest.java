package com.example.savio.testeandroid;

import android.view.View;
import android.widget.TextView;

import com.example.savio.testeandroid.contract.FormContract;
import com.example.savio.testeandroid.presenter.FormPresenter;
import com.example.savio.testeandroid.view.FormFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FormTest {

    @Mock
    private FormContract.Model model;
    @Mock
    private FormContract.View view;
    @Mock
    private FormContract.Presenter presenter;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkDataIsEmptyFromRequest() {

        assertFalse(presenter.testRequestData());
    }
    
}
