package lzacheu.com.br.santanderinvestimento.contact;

import android.view.View;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import lzacheu.com.br.santanderinvestimento.util.ValidatorUtils;

/**
 * Created by luiszacheu on 6/18/18.
 */

public class FieldsUnitTest {

    private List<View> views = new ArrayList<>(0);

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

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

    @Test
    public void fieldEmailValidation_Invalid(){
        Assert.assertFalse(ValidatorUtils.validationEmail("teste"));
    }

    @Test
    public void fieldEmailValidation_Valid(){
        Assert.assertTrue(ValidatorUtils.validationEmail("teste@gmail.com"));
    }

    @Test
    public void fieldInputTextValidation_Invalid(){
        Assert.assertFalse(ValidatorUtils.validationText(""));
    }

    @Test
    public void fieldInputTextValidation_Valid(){
        Assert.assertTrue(ValidatorUtils.validationText("Alguma coisa"));
    }



}
