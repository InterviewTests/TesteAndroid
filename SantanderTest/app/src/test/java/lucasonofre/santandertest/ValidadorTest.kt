package lucasonofre.santandertest

import lucasonofre.santandertest.validacao.Validador
import org.junit.Test

import org.junit.Assert.*

class ValidadorTest {

    @Test
    fun testCampoVazio() {
        val campo = ""
        val validador = Validador()

        val resultado = validador.validaCampoVazio(campo)
        assertTrue(resultado)
    }

    @Test
    fun testCampoNaoVazio() {
        val campo = "Teste"
        val validador = Validador()

        val resultado = validador.validaCampoVazio(campo)
        assertFalse(resultado)
    }

//    @Test
//    fun testeValidaEmail() {
//
//        val email     = "teste@teste.com"
//        val validador = Validador()
//
//        val resultado = validador.validaEmail(email)
//        assertTrue(resultado)
//    }


    @Test
    fun testeValidaDigitos() {
        val contagemDigitos = 11
        val validador = Validador()

        val resultado = validador.validaDigitos(contagemDigitos)
        assertTrue(resultado)
    }

    @Test
    fun testeValidaDigitosIncorreto() {
        val contagemDigitos = 9
        val validador = Validador()

        val resultado = validador.validaDigitos(contagemDigitos)
        assertTrue(resultado)
    }

}