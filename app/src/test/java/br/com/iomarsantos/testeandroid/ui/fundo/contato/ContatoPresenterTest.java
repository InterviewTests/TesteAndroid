package br.com.iomarsantos.testeandroid.ui.fundo.contato;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.iomarsantos.testeandroid.data.Repository;
import br.com.iomarsantos.testeandroid.data.model.CellResponse;
import br.com.iomarsantos.testeandroid.entity.Cell;
import br.com.iomarsantos.testeandroid.ui.entity.MockModelCell;
import br.com.iomarsantos.testeandroid.ui.utils.rx.TestSchedulerProvider;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.ArgumentMatchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ContatoPresenterTest {

    @Mock
    ContatoView mMockContatoView;

    @Mock
    Repository mMockRepository;

    private ContatoPresenter<ContatoView> mContatoPresenter;

    private TestScheduler mTestScheduler;

    @BeforeClass
    public static void onlyOnce() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mContatoPresenter = new ContatoPresenter<>(mMockRepository, testSchedulerProvider, compositeDisposable);
        mContatoPresenter.onAttach(mMockContatoView);
    }

    @Test
    public void loadCellsSuccessful() {

        CellResponse cellResponse = MockModelCell.newCellResponse(1);

        stubRepositoryGetCells(Single.just(cellResponse));

        mContatoPresenter.findAllCellsApiCall();

        mTestScheduler.triggerActions();

        verify(mMockContatoView).showLoading();
        verify(mMockContatoView).createViews(cellResponse.getCells());
        verify(mMockContatoView).hideLoading();
    }

    @Test
    public void loadCellsEmpty() {

        CellResponse cellResponse = MockModelCell.newCellResponse(0);

        stubRepositoryGetCells(Single.just(cellResponse));

        mContatoPresenter.findAllCellsApiCall();

        mTestScheduler.triggerActions();

        verify(mMockContatoView).showLoading();
        verify(mMockContatoView).createViews(cellResponse.getCells());
        verify(mMockContatoView).hideLoading();

    }

    @Test
    public void loadCellsFail() {
        stubRepositoryGetCells(Single.error(new Exception()));

        mContatoPresenter.findAllCellsApiCall();

        mTestScheduler.triggerActions();

        verify(mMockContatoView).showLoading();
        verify(mMockContatoView, never()).createViews(anyListOf(Cell.class));
        verify(mMockContatoView).hideLoading();

    }

    private void stubRepositoryGetCells(Single single) {
        doReturn(single)
                .when(mMockRepository)
                .getCellApiCall();
    }

    @After
    public void tearDown() throws Exception {
        mContatoPresenter.onDetach();
    }

}