package br.com.rafael.santanderteste

import android.util.Log
import br.com.rafael.santanderteste.domain.entity.Fund
import br.com.rafael.santanderteste.domain.entity.GeneralInfo
import br.com.rafael.santanderteste.domain.entity.ScreenFund
import br.com.rafael.santanderteste.helper.ViewHelper
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito
import org.mockito.Mockito.*

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
    fun email_shoud_be_valid() {
        assertTrue(ViewHelper.isEmailValid("rafael.felipe1989@gmail.com"))
        assertTrue(ViewHelper.isEmailValid("nome@teste.com"))
        assertTrue(ViewHelper.isEmailValid("nome@aws"))
    }

    @Test
    fun te() {
        val mock = Mockito.mock(Fund::class.java)

        `when`(mock.definition).thenReturn("teste")

        System.out.println(mock.fundName);
    }
}
