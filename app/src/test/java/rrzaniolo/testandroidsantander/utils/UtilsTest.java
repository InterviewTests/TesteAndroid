package rrzaniolo.testandroidsantander.utils;

/*
 * Created by rrzaniolo on 09/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UtilsTest {

    private String EMPTY    = "";
    private String NULL     = null;
    private String STRING   = "string";

    @Test
    public void isNotNullNorEmpty(){
        assertFalse(Utils.isNotNullNorEmpty(EMPTY));
        assertFalse(Utils.isNotNullNorEmpty(NULL));
        assertTrue(Utils.isNotNullNorEmpty(STRING));
    }
}
