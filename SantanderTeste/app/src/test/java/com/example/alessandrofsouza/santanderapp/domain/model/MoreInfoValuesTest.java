package com.example.alessandrofsouza.santanderapp.domain.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MoreInfoValuesTest {

//    private static final Double d = 0.1;
    MoreInfoValues moreinfo;

    @Before
    public void init() {
        moreinfo = new MoreInfoValues();
        moreinfo.setFund(0.1);
    }

    @Test
    public void fund_ReturnsTrue() {
        //assertTrue(moreinfo.getFund() == d);
        assertThat(moreinfo.getFund(), is(0.1));
    }


    @Test
    public void cdi_ReturnsTrue() {
//        assertTrue(moreinfo.getFund() == d);
        assertThat(moreinfo.getFund(), is(0.1));
    }


}