package com.example.alessandrofsouza.santanderapp.presentation.utils;

import org.junit.Test;
import org.mockito.Matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EmailValidatorTest {

//    String validMailcom = "name@email.com";
//    String validMailComBR = "name@email.com.br";
//    String invalidMailNull = null;
//    String invalidMailEmpty = "";
//    String invalidMailNoAt = "email.com.br";
//    String invalidMailDoubleAt = "email@@.com.br";

    //RIGHTS
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
//        assertTrue(EmailValidator.validateEmail(validMailcom));
//        assertEquals(EmailValidator.validateEmail(validMailcom), true);
        assertThat(EmailValidator.validateEmail("name@email.com"), is(true));
    }

    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
//        assertTrue(EmailValidator.validateEmail(validMailComBR));
//        assertEquals(EmailValidator.validateEmail(validMailComBR), true);
        assertThat(EmailValidator.validateEmail("name@email.com.br"), is(true));
    }

    //WRONGS
    @Test
    public void emailValidator_NullEmail_ReturnsFalse() {
//        assertFalse(EmailValidator.validateEmail(invalidMailNull));
        assertEquals(EmailValidator.validateEmail(null), false);
    }

    @Test
    public void emailValidator_NullEmpty_ReturnsFalse() {
//        assertFalse(EmailValidator.validateEmail(invalidMailEmpty));
        assertEquals(EmailValidator.validateEmail(""), false);
    }

    @Test
    public void emailValidator_IncorrectEmailNoAt_ReturnsFalse() {
//        assertFalse(!EmailValidator.validateEmail(validMailComBR));
        assertEquals(EmailValidator.validateEmail("email.com.br"), false);
    }

    @Test
    public void emailValidator_IncorrectEmailDoubleAt_ReturnsFalse() {
//        assertFalse(!EmailValidator.validateEmail(validMailComBR));
        assertEquals(EmailValidator.validateEmail("email@@.com.br"), false);
    }
}