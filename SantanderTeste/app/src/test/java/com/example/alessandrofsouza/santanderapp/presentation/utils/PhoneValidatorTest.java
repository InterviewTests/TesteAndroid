package com.example.alessandrofsouza.santanderapp.presentation.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class PhoneValidatorTest {
    //PHONE
    @Test
    public void phoneValidator_Correct9Digits_ReturnsTrue() {
        assertTrue(PhoneValidator.validatePhone("00912345678"));
    }

    @Test
    public void phoneValidator_Correct8Digits_ReturnsTrue() {
        assertTrue(PhoneValidator.validatePhone("0012345678"));
    }

    @Test
    public void phoneValidator_WrongMoreThen9Digits_ReturnsFalse() {
        assertFalse(PhoneValidator.validatePhone("009123456789"));
    }

    @Test
    public void phoneValidator_WrongLessThen8Digits_ReturnsFalse() {
        assertFalse(PhoneValidator.validatePhone("12345678"));
    }

    @Test
    public void phoneValidator_EmptyString_ReturnsFalse() {
        assertFalse(PhoneValidator.validatePhone(""));
    }

    @Test
    public void phoneValidator_NullPhone_ReturnsFalse() {
        assertFalse(PhoneValidator.validatePhone(null));
    }

    @Test
    public void phoneValidator_OddDigitPhone_ReturnsFalse() {
        assertFalse(PhoneValidator.validatePhone("01#2222222"));
    }
}