package lzacheu.com.br.santanderinvestimento.contact;

import org.junit.Test;
import org.mockito.Mock;

/**
 * Created by luiszacheu on 6/21/18.
 */

public class ContactPresenterTest {

    private ContactContract.Presenter contactPresenter;

    @Mock
    private ContactContract.View viewPresenter;

    public void setUp(){
        contactPresenter = new ContactPresenter(viewPresenter);
    }

    @Test
    public void validFields_Empty(){

    }

    @Test
    public void validFields_Email_Validation(){

    }

}
