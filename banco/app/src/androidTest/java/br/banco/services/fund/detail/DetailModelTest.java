package br.banco.services.fund.detail;

import org.junit.Before;
import org.junit.Test;

import br.banco.services.app.utils.ValidatorName;

import org.junit.Test;

import static org.junit.Assert.*;

public class DetailModelTest {

    DetailModel model;
    @Before
    public  void setUp() throws Exception{
        model = new DetailModel();
    }

    @Test public void getOrigin() {

       // model.setOrigin(99);
        assertNotNull(model.getOrigin());

         model.setOrigin(0);
         assertEquals(0, model.getOrigin());

         model.setOrigin(1);
         assertEquals(1, model.getOrigin());

         model.setOrigin(555);
         assertEquals(0, model.getOrigin());

    }

    @Test public void getLocation() {

        assertNotNull(model.getLocation());

        model.setLocation(0+555);
        assertEquals(0, model.getLocation());

        model.setLocation(0);
        assertEquals(0, model.getLocation());

        model.setLocation(-555);
        assertEquals(0, model.getLocation());

        model.setLocation(1);
        assertEquals(1, model.getLocation());

        model.setLocation(555);
        assertEquals(0, model.getLocation());

    }

    @Test(expected=java.lang.ArithmeticException.class)
    public void getOrigin_DivideByZero() {
        model.setLocation(555 / 0);
        assertEquals(0, model.getOrigin());
    }


}