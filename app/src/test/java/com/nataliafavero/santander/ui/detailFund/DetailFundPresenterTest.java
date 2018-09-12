package com.nataliafavero.santander.ui.detailFund;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Created by nataliafavero on 11/09/18.
 */
public class DetailFundPresenterTest {

    @Mock
    DetailFundContract.View view;

    DetailFundPresenter presenterTest;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenterTest = new DetailFundPresenter(view);

    }

    @Test
    public void getFund() throws Exception {
        presenterTest.getFund();
        Mockito.verify(view).showFund();
    }

}