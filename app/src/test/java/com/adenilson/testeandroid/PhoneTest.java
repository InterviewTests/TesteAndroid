package com.adenilson.testeandroid;

import com.adenilson.testeandroid.base.EditTextMask;

import org.junit.Test;
import org.mockito.Mock;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 11/06/2018
 *
 * Using Mockito we can test our classes with fake data, that is, the mockito create data simulating a query in database, data from http request( in
 * this case, Mockito can do all the Interactor functions about objects/responses) and etc. So, it make the unit tests easier!
 * Using Roboletric to solve the problem with android.jar (null in Matcher and Pattern object)
 *
 */

public class PhoneTest {

    private final String VALID_NUMBER = "(16) 98239-9366";
    private final String INVALID_NUMBER = "(16) 236-23";

    @Mock
    EditTextMask mask = new EditTextMask();

    @Test
    public void test_validPhoneNumber() {
        String phone = mask.unmask(VALID_NUMBER);
        int length = phone.length();
        assertTrue(length == 8 || length == 9 || length == 10 || length == 11);
    }

    @Test
    public void test_invalidPhoneNumber() {
        String phone = mask.unmask(INVALID_NUMBER);
        int length = phone.length();
        assertFalse(length == 8 || length == 9 || length == 10 || length == 11);
    }


}
