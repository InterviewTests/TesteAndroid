package com.avanade.santander.fundos.presentation;

import com.avanade.santander.UseCaseHandler;
import com.avanade.santander.contato.domain.usecase.GetFormulario;
import com.avanade.santander.contato.presentation.ContatoContract;
import com.avanade.santander.fundos.domain.model.Fundos;
import com.avanade.santander.fundos.domain.model.Info;
import com.avanade.santander.fundos.domain.model.More;
import com.avanade.santander.fundos.domain.model.MoreInfo;
import com.avanade.santander.fundos.domain.model.Screen;
import com.avanade.santander.fundos.domain.usecase.GetFundos;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FundosPresenterTest {


    @Mock
    UseCaseHandler mUuseCaseHandler;

    @Mock
    FundosContract.IView mFundosView;

    @Mock
    GetFundos mGetFundos;

    @Mock
    Screen screen;

    @InjectMocks
    FundosPresenter fundosPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        List<Info> info = new ArrayList<>();
        List<Info> downInfo = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            info.add(new Info("Taxa " + i, "" + (Math.pow(-13, i))));
            downInfo.add(new Info("Documento " + i, "endereço URL"));
        }


        fundosPresenter.FUNDO =
                new Fundos(
                        new Screen(
                                "Titulo",
                                "Nome do Fundo",
                                "O que é",
                                "Definição",
                                "Risco",
                                5,
                                "Informações",
                                new MoreInfo(
                                        new More("Rend fund mes",
                                                "Rend CDI mes"
                                        ),
                                        new More("Rend fund ano",
                                                "Rend CDI ano"
                                        ),
                                        new More("Rend fund 12 meses",
                                                "Rend CDI 12 meses"
                                        )
                                ),
                                info,
                                downInfo

                        )
                );
    }

    @Test
    public void setPresenterNoView() {
        // Then the presenter is set to the view
        verify(mFundosView).setPresenter(fundosPresenter);
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

        verify(mFundosView).setLoadingIndicator(false);
        verify(mFundosView).desenhaTela(fundosPresenter.FUNDO);
    }

    @Test
    public void testRefreshFundos() throws Exception {
        when(mFundosView.isActive()).thenReturn(true);

        fundosPresenter.refreshFundos();
        when(mFundosView.isActive()).thenReturn(true);

        assertNotNull(fundosPresenter.FUNDO);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme