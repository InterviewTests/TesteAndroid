package br.banco.services.fund.detail;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DetailViewTest {

    DetailView detailView;

    @Before
    public void setUp() throws Exception {
        detailView = new DetailView();
    }



    @Test
    public void checkInternet() {

        String INTERNET = (detailView.checkInternet()) ? "SIM" : "NAO";

       // Log.e("TESTSUNIT","INTERNET = " + INTERNET);
        //assertTrue(detailView.checkInternet());
       // assertFalse(detailView.checkInternet());
    }





}