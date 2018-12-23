package com.seletiva.santander.investment;

import com.seletiva.santander.investment.UI.View.Form;
import com.seletiva.santander.investment.UI.View.FormBuilder;
import com.seletiva.santander.investment.UI.View.FormBuilderException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.when;

public class ViewBuilderTest {
    @Mock
    private Form formMock;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = FormBuilderException.class)
    public void testFormBuilderConstructorWithNullParam() throws FormBuilderException {
        new FormBuilder(null);
        thrown.expect(FormBuilderException.class);
    }

    @Test
    public void testFormBuilderConstructorWithNonNullParam() {
        try {
            when(formMock.getInflater()).thenReturn(null);
            new FormBuilder(formMock);
            fail("Expected a FormBuilderException");
        } catch (FormBuilderException e) {
            assertEquals(e.getErrorCode(), FormBuilderException.FormBuilderExceptionCode.INVALID_LAYOUT_INFLATOR);
        }
    }
}
