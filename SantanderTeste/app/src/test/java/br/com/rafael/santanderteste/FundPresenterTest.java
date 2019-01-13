package br.com.rafael.santanderteste;

import br.com.rafael.santanderteste.domain.entity.Fund;
import br.com.rafael.santanderteste.domain.entity.ScreenFund;
import br.com.rafael.santanderteste.presentation.FormContract;
import br.com.rafael.santanderteste.presentation.FunPresenter;
import br.com.rafael.santanderteste.presentation.FundContract;
import br.com.rafael.santanderteste.repository.BankRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.junit.Test;
import org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FundPresenterTest {

    @Mock
    private FundContract.View view;

    private FunPresenter presenter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        presenter = new FunPresenter();
        presenter.setView(view);
        presenter.retrieveInvestimentData();
    }

    @Test
    public void fun() {
        view.loadingInvestimentData();

        Fund fund =mock(Fund.class);

        view.showInvestimentData(new ScreenFund(fund));

}
