package rrzaniolo.testandroidsantander.contact.model.data;

import org.junit.Before;
import org.junit.Test;

import rrzaniolo.testandroidsantander.network.contact.models.data.EmailCell;
import rrzaniolo.testandroidsantander.network.contact.models.response.CellResponse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
 * Created by rrzaniolo on 09/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

public class EmailCellTest {

    private String VALID_EMAIL = "email@email.com";
    private String INVALID_EMAIL = "email.com";

    private EmailCell emailCell;

    @Before
    public void init(){
        emailCell = new EmailCell(new CellResponse());
    }

    @Test
    public void isValidEmail(){
        Boolean isValid = emailCell.validateAnswer(VALID_EMAIL);

        assertTrue(isValid);
    }

    @Test
    public void isInvalidEmail(){
        Boolean isFalse = emailCell.validateAnswer(INVALID_EMAIL);

        assertFalse(isFalse);
    }
}
