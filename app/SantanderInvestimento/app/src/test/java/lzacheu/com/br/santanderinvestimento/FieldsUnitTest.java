package lzacheu.com.br.santanderinvestimento;

import android.view.View;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import lzacheu.com.br.santanderinvestimento.contact.ContactContract;

/**
 * Created by luiszacheu on 6/18/18.
 */

public class FieldsUnitTest {
    private List<View> views = new ArrayList<>(0);

    @Test
    public void fieldsIsEmpty(){
        ContactContract.Presenter contectContract = Mockito.mock(ContactContract.Presenter.class);
        Mockito.when(contectContract.validFields(views)).thenReturn(true);
        boolean isValid = contectContract.validFields(views);
        Assert.assertEquals(true, isValid);

    }

    @Test
    public void fieldsNotIsEmpty(){
        ContactContract.Presenter contectContract = Mockito.mock(ContactContract.Presenter.class);
        Mockito.when(contectContract.validFields(views)).thenReturn(false);
        boolean isValid = contectContract.validFields(views);
        Assert.assertEquals(false, isValid);

    }

}
