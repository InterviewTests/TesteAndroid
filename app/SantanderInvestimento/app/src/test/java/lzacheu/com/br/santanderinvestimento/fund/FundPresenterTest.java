package lzacheu.com.br.santanderinvestimento.fund;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import lzacheu.com.br.santanderinvestimento.data.FundDataSource;
import lzacheu.com.br.santanderinvestimento.data.FundRepository;
import lzacheu.com.br.santanderinvestimento.model.fund.DownInfo;
import lzacheu.com.br.santanderinvestimento.model.fund.Info;
import lzacheu.com.br.santanderinvestimento.model.fund.MoreInfo;
import lzacheu.com.br.santanderinvestimento.model.fund.MoreInfoDetail;
import lzacheu.com.br.santanderinvestimento.model.fund.Screen;

import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by luiszacheu on 6/21/18.
 */

public class FundPresenterTest {

    private FundInfoContract.Presenter fundPresenter;

    @Mock
    private FundInfoContract.View fundView;

    @Mock
    private FundRepository fundRepository;

    @Captor
    private ArgumentCaptor<FundDataSource.LoadFundCallback> loadFundCallback;

    private Screen screen;

    @Before
    public void setUpPresenter(){
        MockitoAnnotations.initMocks(this);
        fundPresenter = new FundInfoPresenter(fundRepository, fundView);

        MoreInfoDetail month = new MoreInfoDetail(0.3d, 0.4d);
        MoreInfoDetail year = new MoreInfoDetail(0.3d, 0.4d);
        MoreInfoDetail _12month = new MoreInfoDetail(0.3d, 0.4d);

        List<Info> infos = new ArrayList<>();
        infos.add(new Info("teste", "Esse é um teste!"));

        List<DownInfo> downInfos = new ArrayList<>();
        downInfos.add(new DownInfo("teste", "Esse é um teste!"));


        screen = new Screen("Tela de investimentos",
                "Fundo ABC", "O que é? ", "Lorem ipsum Lorem ipsum",
                "Qual o risco", 3, "Lorem ipsum",
                new MoreInfo(month, year, _12month), infos, downInfos);


    }

    @Test
    public void fetchDataFromRepositoryAndBindViews(){
        fundPresenter.fetchData();

        verify(fundRepository).getFunds(loadFundCallback.capture());
        loadFundCallback.getValue().onFundsLoaded(screen);

        verify(fundView).bindValues(screen);

    }

    @Test
    public void fetchData_With_NullData(){
        fundPresenter.fetchData();

        verify(fundRepository).getFunds(loadFundCallback.capture());
        loadFundCallback.getValue().onFundsLoaded(null);

        verify(fundView).bindValues(null);
    }


}
