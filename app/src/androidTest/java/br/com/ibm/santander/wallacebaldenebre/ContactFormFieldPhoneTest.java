package br.com.ibm.santander.wallacebaldenebre;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.ibm.santander.wallacebaldenebre.utils.Helper;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class ContactFormFieldPhoneTest {
    @Test
    public void nameValidator_CorrectPhoneSimple_ReturnsTrue() {
        assertTrue(Helper.isPhone("(11)96782-8806"));
    }

    @Test
    public void nameValidator_CorrectPhoneEightDigits_ReturnsTrue() {
        assertTrue(Helper.isPhone("(11)6782-8806"));
    }

    @Test
    public void nameValidator_CorrectPhoneNineDigits_ReturnsTrue() {
        assertTrue(Helper.isPhone("(11)96782-8806"));
    }

    @Test
    public void nameValidator_InvalidPhoneTotalDigits_ReturnsFalse() {
        assertFalse(Helper.isPhone("(11)96782-08806"));
    }

    @Test
    public void nameValidator_InvalidPhoneFormat_ReturnsFalse() {
        assertFalse(Helper.isPhone("1196782-8806"));
    }
}
