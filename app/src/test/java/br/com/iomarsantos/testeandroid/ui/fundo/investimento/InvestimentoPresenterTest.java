package br.com.iomarsantos.testeandroid.ui.fundo.investimento;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.iomarsantos.testeandroid.data.Repository;
import br.com.iomarsantos.testeandroid.data.model.ScreenResponse;
import br.com.iomarsantos.testeandroid.entity.InvestmentFund;
import br.com.iomarsantos.testeandroid.ui.entity.MockModelScreen;
import br.com.iomarsantos.testeandroid.ui.fundo.investimentos.InvestimentoPresenter;
import br.com.iomarsantos.testeandroid.ui.fundo.investimentos.InvestimentoView;
import br.com.iomarsantos.testeandroid.ui.utils.rx.TestSchedulerProvider;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InvestimentoPresenterTest {

    @Mock
    InvestimentoView mMockInvestimentoView;

    @Mock
    Repository mMockRepository;

    private InvestimentoPresenter<InvestimentoView> mInvestimentoPresenter;

    private TestScheduler mTestScheduler;

    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mInvestimentoPresenter = new InvestimentoPresenter<>(mMockRepository, testSchedulerProvider, compositeDisposable);
        mInvestimentoPresenter.onAttach(mMockInvestimentoView);
    }

    @Test
    public void loadFundSuccessful() {

        ScreenResponse screenResponse = MockModelScreen.newScreenResponse(1);

        stubRepositoryGetFund(Single.just(screenResponse));

        mInvestimentoPresenter.findAllFundApiCall();

        mTestScheduler.triggerActions();

        verify(mMockInvestimentoView).showLoading();
        verify(mMockInvestimentoView).updateVies(screenResponse.getScreen());
        verify(mMockInvestimentoView).hideLoading();
    }

    @Test
    public void loadFundEmpty() {

        ScreenResponse screenResponse = MockModelScreen.newScreenResponse(0);

        stubRepositoryGetFund(Single.just(screenResponse));

        mInvestimentoPresenter.findAllFundApiCall();

        mTestScheduler.triggerActions();

        verify(mMockInvestimentoView).showLoading();
        verify(mMockInvestimentoView).updateVies(screenResponse.getScreen());
        verify(mMockInvestimentoView).hideLoading();

    }

    @Test
    public void loadFundFail() {
        stubRepositoryGetFund(Single.error(new Exception()));

        mInvestimentoPresenter.findAllFundApiCall();

        mTestScheduler.triggerActions();

        verify(mMockInvestimentoView).showLoading();
        verify(mMockInvestimentoView, never()).updateVies(any(InvestmentFund.class));
        verify(mMockInvestimentoView).hideLoading();

    }

    private void stubRepositoryGetFund(Single single) {
        doReturn(single)
                .when(mMockRepository)
                .getFundApiCall();
    }

    @After
    public void tearDown() throws Exception {
        mInvestimentoPresenter.onDetach();
    }

}