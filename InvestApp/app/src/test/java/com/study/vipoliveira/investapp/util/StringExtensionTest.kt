package com.study.vipoliveira.investapp.util

import junit.framework.TestCase
import org.junit.Test

class StringExtensionTest{

    val EMPTY_TEXT: String = ""
    val NULL_TEXT: String? = null
    val TEXT: String = "Vitor Oliveira"
    val VALID_EMAIL: String = "vipoliveira@gmail.com"
    val WRONG_EMAIL: String = "vtr@"
    val WRONG_PHONE = "1"
    val VALID_PHONE = "(00)12345678"
    val VALID_PHONE_11_CHARACTERS = "(00)123456789"

    @Test
    fun checkIfFilesdAreValid() {
        TestCase.assertTrue(VALID_EMAIL.isValidEmail())
        TestCase.assertTrue(VALID_PHONE.isValidPhoneNumber())
        TestCase.assertTrue(VALID_PHONE_11_CHARACTERS.isValidPhoneNumber())
        TestCase.assertTrue(TEXT.isValidText())
    }

    @Test
    fun checkIfFilesdAreInvalid() {
        TestCase.assertFalse(WRONG_EMAIL.isValidEmail())
        TestCase.assertFalse(WRONG_PHONE.isValidPhoneNumber())
        TestCase.assertFalse(EMPTY_TEXT.isValidText())
        TestCase.assertFalse(NULL_TEXT!!.isValidText())
    }

}