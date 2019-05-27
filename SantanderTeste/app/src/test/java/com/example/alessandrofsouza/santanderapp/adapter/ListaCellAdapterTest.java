package com.example.alessandrofsouza.santanderapp.adapter;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ListaCellAdapterTest {


    //NAME
    @Test
    public void nameValidator_CorrectNameSimple_ReturnsTrue() {
        assertTrue(ListaCellAdapter.validateName("name"));
    }

    @Test
    public void nameValidator_CorrectNameDouble_ReturnsTrue() {
        assertTrue(ListaCellAdapter.validateName("name name"));
    }

    @Test
    public void thirdValidator_CorrectNameThird_ReturnsTrue() {
        assertTrue(ListaCellAdapter.validateName("name name name"));
    }

    @Test
    public void nameValidator_InvalidName_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validateName("2@email.com"));
    }

    @Test
    public void nameValidator_EmptyString_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validateName(""));
    }

    @Test
    public void nameValidator_NullName_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validateName(null));
    }



    //EMAIL
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(ListaCellAdapter.validateEmail("name@email.com"));
    }

    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(ListaCellAdapter.validateEmail("name@email.co.uk"));
    }

    @Test
    public void emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validateEmail("name@email"));
    }

    @Test
    public void emailValidator_InvalidEmailMoreCaractersThenPossible_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validateEmail("name@email.com.br.br"));
    }

    @Test
    public void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validateEmail("name@email..com"));
    }

    @Test
    public void emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validateEmail("@email.com"));
    }

    @Test
    public void emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validateEmail(""));
    }

    @Test
    public void emailValidator_NullEmail_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validateEmail(null));
    }



    //PHONE
    @Test
    public void phoneValidator_Correct9Digits_ReturnsTrue() {
        assertTrue(ListaCellAdapter.validatePhone("00912345678"));
    }

    @Test
    public void phoneValidator_Correct8Digits_ReturnsTrue() {
        assertTrue(ListaCellAdapter.validatePhone("0012345678"));
    }

    @Test
    public void phoneValidator_WrongMoreThen9Digits_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validatePhone("009123456789"));
    }

    @Test
    public void phoneValidator_WrongLessThen8Digits_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validatePhone("12345678"));
    }

    @Test
    public void phoneValidator_EmptyString_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validatePhone(""));
    }

    @Test
    public void phoneValidator_NullPhone_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validatePhone(null));
    }

    @Test
    public void phoneValidator_OddDigitPhone_ReturnsFalse() {
        assertFalse(ListaCellAdapter.validatePhone("01#2222222"));
    }


}