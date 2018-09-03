package br.com.testeandroid;

import org.junit.Assert;
import org.junit.Test;

import br.com.testeandroid.utils.Validador;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private Validador validador = new Validador();
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void ValidarTelefoneSucesso(){
        Assert.assertTrue(validador.ValidarTelefone("(11)1111-1111"));
        Assert.assertTrue(validador.ValidarTelefone("(11)11111-1111"));
        Assert.assertFalse(validador.ValidarTelefone("(11)1111-111"));
    }

    @Test
    public void ValidarTelefoneFalha(){
        Assert.assertFalse(validador.ValidarTelefone("(11)1111-111"));
    }

    @Test
    public void ValidarEmailSucesso(){
        Assert.assertTrue(validador.ValidarEmail("guilherm@gmail.com"));
    }

    @Test
    public void ValidarEmailFalha(){
        Assert.assertFalse(validador.ValidarEmail("guilhermgmail.com"));
    }
}