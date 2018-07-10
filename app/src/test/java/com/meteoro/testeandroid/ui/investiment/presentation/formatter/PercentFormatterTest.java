package com.meteoro.testeandroid.ui.investiment.presentation.formatter;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class PercentFormatterTest {

    @Mock
    Context context;

    private PercentFormatter formatter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        formatter = new PercentFormatter(context);
    }

    @Test
    public void formatValue() {
        double value = 7.4;
        String expected = "7.40 %";

        when(context.getString(anyInt())).thenReturn("%.2f %%");
        assertEquals(expected, formatter.format(value));
    }
}
