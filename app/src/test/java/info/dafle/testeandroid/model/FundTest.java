package info.dafle.testeandroid.model;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FundTest {

    @Test
    public void checkQuantityLabel() {

        Fund.Screen fund = mock(Fund.Screen.class);
        when(fund.titles()).thenReturn(new String[5]);
        Assert.assertEquals(fund.titles().length, 5);
    }
}
