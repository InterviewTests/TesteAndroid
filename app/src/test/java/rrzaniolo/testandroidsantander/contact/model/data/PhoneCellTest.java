package rrzaniolo.testandroidsantander.contact.model.data;

import org.junit.Before;
import org.junit.Test;

import rrzaniolo.testandroidsantander.network.contact.models.data.PhoneCell;
import rrzaniolo.testandroidsantander.network.contact.models.response.CellResponse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
 * Created by rrzaniolo on 09/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

public class PhoneCellTest {

    private String VALID_PHONE = "16997980000";
    private String INVALID_PHONE = "12";

    private PhoneCell phoneCell;

    @Before
    public void init(){
        phoneCell = new PhoneCell(new CellResponse());
    }

    @Test
    public void isValidPhone(){
        Boolean isValid = phoneCell.validateAnswer(VALID_PHONE);

        assertTrue(isValid);
    }

    @Test
    public void isInvalidPhone(){
        Boolean isFalse = phoneCell.validateAnswer(INVALID_PHONE);

        assertFalse(isFalse);
    }
}
