package com.santander.luizlago.testeandroid

import com.santander.luizlago.testeandroid.helpers.ValidationHelper
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun ValidarTelefoneSucesso() {
        Assert.assertFalse(ValidationHelper.isValidPhoneNumber("(11) 1111-1111"))
        Assert.assertFalse(ValidationHelper.isValidPhoneNumber("(11) 11111-1111"))
        Assert.assertTrue(ValidationHelper.isValidPhoneNumber("(11) 1111-1"))
    }

    @Test
    fun ValidarTelefoneFalha() {
        Assert.assertTrue(ValidationHelper.isValidPhoneNumber("(11)1111-1"))
    }

    @Test
    fun ValidarEmailSucesso() {
        Assert.assertTrue(ValidationHelper.isValidEmail("guilherm@gmail.com"))
    }

    @Test
    fun ValidarEmailFalha() {
        Assert.assertFalse(ValidationHelper.isValidEmail("guilhermgmail.com"))
    }

}
