package com.example.alessandrofsouza.santanderapp.presentation.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class NameValidatorTest {

    //NAME
    @Test
    public void nameValidator_CorrectNameSimple_ReturnsTrue() {
        assertTrue(NameValidator.validateName("name"));
    }

    @Test
    public void nameValidator_CorrectNameDouble_ReturnsTrue() {
        assertTrue(NameValidator.validateName("name name"));
    }

    @Test
    public void thirdValidator_CorrectNameThird_ReturnsTrue() {
        assertTrue(NameValidator.validateName("name name name"));
    }

    @Test
    public void nameValidator_InvalidName_ReturnsFalse() {
        assertFalse(NameValidator.validateName("2@email.com"));
    }

    @Test
    public void nameValidator_EmptyString_ReturnsFalse() {
        assertFalse(NameValidator.validateName(""));
    }

    @Test
    public void nameValidator_NullName_ReturnsFalse() {
        assertFalse(NameValidator.validateName(null));
    }

}