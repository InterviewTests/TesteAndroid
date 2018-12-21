package com.avanade.santander.fundos.presentation;

import com.avanade.santander.UseCaseHandler;
import com.avanade.santander.fundos.domain.model.Fundos;
import com.avanade.santander.fundos.domain.usecase.GetFundos;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class FundosPresenterTest {
    @Mock
    FundosContract.IView mFundosView;
    @Mock
    GetFundos mGetFundos;
    @Mock
    UseCaseHandler mUseCaseHandler;
    @Mock
    Fundos FUNDO;
    @InjectMocks
    FundosPresenter fundosPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStart() throws Exception {
        when(mFundosView.isActive()).thenReturn(true);

        fundosPresenter.start();
    }

    @Test
    public void testShowFundos() throws Exception {
        when(mFundosView.isActive()).thenReturn(true);

        fundosPresenter.showFundos();
    }

    @Test
    public void testRefreshFundos() throws Exception {
        when(mFundosView.isActive()).thenReturn(true);

        fundosPresenter.refreshFundos();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme