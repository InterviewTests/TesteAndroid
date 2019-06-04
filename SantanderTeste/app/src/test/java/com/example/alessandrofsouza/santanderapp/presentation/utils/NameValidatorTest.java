package com.example.alessandrofsouza.santanderapp.presentation.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NameValidatorTest {

//    String validName = "name";
//    String validNameSurname = "name name";
//    String validNameSurnameLastName = "name name name";

    //NAME
    @Test
    public void nameValidator_CorrectNameSimple_ReturnsTrue() {
//        assertTrue(NameValidator.validateName("name"));
        assertThat(NameValidator.validateName("name"), is(true));
    }

    @Test
    public void nameValidator_CorrectNameDouble_ReturnsTrue() {
//        assertTrue(NameValidator.validateName("name name"));
        assertThat(NameValidator.validateName("name name"), is(true));
    }

    @Test
    public void thirdValidator_CorrectNameThird_ReturnsTrue() {
//        assertTrue(NameValidator.validateName("name name name"));
        assertThat(NameValidator.validateName("name name name"), is(true));
    }

    @Test
    public void nameValidator_InvalidName_ReturnsFalse() {
//        assertFalse(NameValidator.validateName("2@email.com"));
        assertThat(NameValidator.validateName("2@mali&"), is(false));
    }

    @Test
    public void nameValidator_EmptyString_ReturnsFalse() {
//        assertFalse(NameValidator.validateName(""));
        assertEquals(NameValidator.validateName(""), false);
    }

    @Test
    public void nameValidator_NullName_ReturnsFalse() {
//        assertFalse(NameValidator.validateName(null));
        assertEquals(NameValidator.validateName(null), false);
    }

}