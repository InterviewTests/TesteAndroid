package com.seletiva.santander.investment;

import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.seletiva.santander.investment.UI.View.Form;
import com.seletiva.santander.investment.UI.View.FormBuilder;
import com.seletiva.santander.investment.UI.View.FormBuilderException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class ViewBuilderTest implements Form {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = FormBuilderException.class)
    public void testFormBuilderConstructorWithNullParam() throws FormBuilderException {
        new FormBuilder(null);
        thrown.expect(FormBuilderException.class);
    }

    @Test
    public void testFormBuilderConstructorWithNonNullParam() {
        try {
            new FormBuilder(this);
            fail("Expected a FormBuilderException");
        } catch (FormBuilderException e) {
            assertEquals(e.getErrorCode(), FormBuilderException.FormBuilderExceptionCode.INVALID_LAYOUT_INFLATOR);
        }
    }

    @Override
    public LinearLayout getFormContainer() {
        return null;
    }

    @Override
    public LayoutInflater getInflater() { return null; }
}
