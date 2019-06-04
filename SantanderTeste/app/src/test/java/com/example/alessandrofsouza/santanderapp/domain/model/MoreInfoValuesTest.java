package com.example.alessandrofsouza.santanderapp.domain.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoreInfoValuesTest {

    Double d;
    MoreInfoValues moreinfo = new MoreInfoValues();

    @Test
    public void fund_ReturnsTrue() {
        assertTrue(moreinfo.getFund() == d);
    }

    @Test
    public void fund_ReturnsFalse() {
        assertFalse(moreinfo.getCDI() != d);
    }


    @Test
    public void cdi_ReturnsTrue() {
        assertTrue(moreinfo.getFund() == d);
    }

    @Test
    public void cdi_ReturnsFalse() {
        assertFalse(moreinfo.getCDI() != d);
    }

}