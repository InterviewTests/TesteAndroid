package com.adenilson.testeandroid;

import com.adenilson.testeandroid.util.StringUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 11/06/2018
 *
 * Using Mockito we can test our classes with fake data, that is, the mockito create data simulating a query in database, data from http request( in
 * this case, Mockito can do all the Interactor functions about objects/responses) and etc. So, it make the unit tests easier!
 * Using Roboletric to solve the problem with android.jar (null in Matcher and Pattern object)
 */

@RunWith(RobolectricTestRunner.class)
public class EmailTest {


    private final String VALID_EMAIL = "adenilson@gmail.com";
    private final String INVALID_EMAIL = "adenilson";


    @Test
    public void test_validEmail() {
        boolean validEmail = StringUtils.isValidEmail(VALID_EMAIL);
        assertTrue(validEmail);
    }

    @Test
    public void test_invalidEmail() {
        boolean validEmail = StringUtils.isValidEmail(INVALID_EMAIL);
        assertFalse(validEmail);
    }

}
