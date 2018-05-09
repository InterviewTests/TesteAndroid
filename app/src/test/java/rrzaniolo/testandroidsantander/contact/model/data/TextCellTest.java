package rrzaniolo.testandroidsantander.contact.model.data;

import org.junit.Before;
import org.junit.Test;

import rrzaniolo.testandroidsantander.network.contact.models.data.TextCell;
import rrzaniolo.testandroidsantander.network.contact.models.response.CellResponse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
 * Created by rrzaniolo on 09/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

public class TextCellTest {

    private String VALID_TEX = "This is a valid text";
    private String INVALID_TEXT = "";

    private TextCell textCell;

    @Before
    public void init(){
        textCell = new TextCell(new CellResponse());
    }

    @Test
    public void isValidText(){
        Boolean isValid = textCell.validateAnswer(VALID_TEX);

        assertTrue(isValid);
    }

    @Test
    public void isInvalidText(){
        Boolean isFalse = textCell.validateAnswer(INVALID_TEXT);

        assertFalse(isFalse);
    }
}
