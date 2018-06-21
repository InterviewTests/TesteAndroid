package lzacheu.com.br.santanderinvestimento.fund;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by luiszacheu on 6/21/18.
 */

public class FundPresenterTest {

    private FundInfoContract.Presenter fundPresenter;

    @Mock
    private FundInfoContract.View fundView;

    public void setUp(){
        MockitoAnnotations.initMocks(this);
        fundPresenter = new FundInfoPresenter(fundView);
    }

    @Test
    public void fetchData_With_EmptyData(){
        fundPresenter.fetchData();
    }

    @Test
    public void fetchData_With_NullData(){

    }

    @Test
    public void fetchData_With_Data(){

    }




}
