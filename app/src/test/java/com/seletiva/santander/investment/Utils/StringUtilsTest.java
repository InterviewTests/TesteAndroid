package com.seletiva.santander.investment.Utils;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {
    private String completePhoneNumber;
    private String completeLongPhoneNumber;
    private String expectedCompletePhoneNumber;
    private String expectedCompleteLongPhoneNumber;

    @Before
    public void setUp() {
        completePhoneNumber = "1134354647";
        completeLongPhoneNumber = "11968445978";
        expectedCompletePhoneNumber = "11 3435-4647";
        expectedCompleteLongPhoneNumber = "11 96844-5978";
    }

    @Test
    public void testIncompletePhoneNumber() {
        String incompletePhoneNumber = "99999";
        String formattedPhoneNumber = StringUtils.formatAsPhoneNumber(incompletePhoneNumber);

        assertEquals(incompletePhoneNumber, formattedPhoneNumber);
    }

    @Test
    public void testCompletePhoneNumber() {
        String formattedPhoneNumber = StringUtils.formatAsPhoneNumber(completePhoneNumber);
        assertEquals(expectedCompletePhoneNumber, formattedPhoneNumber);
    }

    @Test
    public void testCompleteLongPhoneNumber() {
        String formattedPhoneNumber = StringUtils.formatAsPhoneNumber(completeLongPhoneNumber);
        assertEquals(expectedCompleteLongPhoneNumber, formattedPhoneNumber);
    }

    @Test
    public void testRemovePhoneFormat() {
        assertEquals(completePhoneNumber, StringUtils.turnFormattedPhoneNumberToRawString(expectedCompletePhoneNumber));
        assertEquals(completeLongPhoneNumber, StringUtils.turnFormattedPhoneNumberToRawString(expectedCompleteLongPhoneNumber));
    }

    @Test
    public void testEmailValidation() {
        String[] validEmailList = {"nome@domain.com", "name@domain.com.br"};
        String[] invalidEmailList = {"name.com.br", "nome@mail"};

        for(String validEmail:validEmailList) {
            assertTrue(StringUtils.validateEmailAdress(validEmail));
        }

        for(String invalidMail:invalidEmailList) {
            assertFalse(StringUtils.validateEmailAdress(invalidMail));
        }
    }
}