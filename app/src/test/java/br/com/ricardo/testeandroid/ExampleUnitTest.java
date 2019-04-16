package br.com.ricardo.testeandroid;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.ricardo.testeandroid.view.ContactFragment;


@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {

    @Mock
    ContactFragment fragment;


    @Before
    public void setUp() throws Exception {

        fragment = new ContactFragment();
        Assert.assertNotNull(fragment);
        Assert.assertTrue(true);
    }

    @Test
    public void test() throws Exception{
        Assert.assertNotNull(fragment);
        Assert.assertTrue(true);
    }


}