package br.com.ibm.santander.wallacebaldenebre;

import android.support.test.runner.AndroidJUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.ibm.santander.wallacebaldenebre.utils.Helper;

@RunWith(AndroidJUnit4.class)
public class ContactFormFieldNameTest {
    @Test
    public void nameValidator_CorrectNameSimple_ReturnsTrue() {
        assertTrue(Helper.isName("Wallace"));
    }

    @Test
    public void nameValidator_CorrectNameCompost_ReturnsTrue() {
        assertTrue(Helper.isName("Wallace Baldenebre"));
    }

    @Test
    public void nameValidator_InvalidNameSimpleWithNumberAndSpecialChar_ReturnsFalse() {
        assertFalse(Helper.isName("W4ll4c&"));
    }

    @Test
    public void nameValidator_InvalidNameSimpleLengthMoreThanTwo_ReturnsFalse() {
        assertFalse(Helper.isName("Wa"));
    }
}
