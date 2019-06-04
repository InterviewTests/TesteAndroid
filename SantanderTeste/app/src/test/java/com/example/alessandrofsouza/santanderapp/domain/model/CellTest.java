package com.example.alessandrofsouza.santanderapp.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CellTest {

//    int i;
//    String str;
//    boolean bool;

    Cell cell;

    @Before
    public void init() {
        cell = new Cell();
    }

    @Test
    public void id_ReturnsTrue() {
//        assertTrue(cell.getId() == i);
        assertThat(cell.getId(), is(1));
    }

    @Test
    public void type_ReturnsTrue() {
//        assertTrue(cell.getType() == i);
        assertThat(cell.getType(), is(1));
    }

    @Test
    public void message_ReturnsTrue() {
//        assertTrue(cell.getMessage() == str);
        assertThat(cell.getMessage(), is("string"));
    }

    @Test
    public void message_ReturnsFalse() {
//        assertFalse(cell.getMessage() != str);
//        assertFalse(cell.getMessage() == "");
        assertEquals(cell.getMessage() == "", false);
    }



    @Test
    public void typeField_ReturnsTrue() {
//        assertTrue(cell.getTypefield() == str);
        assertThat(cell.getTypefield(), is("string"));
    }

    @Test
    public void typeField_ReturnsFalse() {
//        assertFalse(cell.getTypefield() != str);
//        assertFalse(cell.getTypefield() == "");
        assertEquals(cell.getTypefield() == "", false);
    }

    @Test
    public void hidden_ReturnsTrue() {
//        assertFalse(cell.isHidden() == !bool);
        assertThat(cell.isHidden(), is(true));
    }

    @Test
    public void hidden_ReturnsFalse() {
//        assertFalse(cell.isHidden() == !bool);
        assertThat(cell.isHidden(), is(false));
    }

    @Test
    public void topSpacing_ReturnsTrue() {
//        assertTrue(cell.getTopSpacing() == i);
        assertThat(cell.getTopSpacing(), is(1));
    }

    @Test
    public void show_ReturnsTrue() {
//        assertTrue(cell.getShow() == i);
        assertThat(cell.getShow(), is(1));
    }

    @Test
    public void required_ReturnsTrue() {
//        assertFalse(cell.isRequired() == !bool);
        assertThat(cell.isRequired(), is(true));
    }

    @Test
    public void required_ReturnsFalse() {
//        assertFalse(cell.isRequired() == !bool);
        assertThat(cell.isRequired(), is(false));
    }

    @Test
    public void getEditTextValue_ReturnsTrue() {
//        assertTrue(cell.getEditTextValue() == str);
        assertThat(cell.getEditTextValue(), is("string"));
    }

    @Test
    public void getEditTextValue_ReturnsFalse() {
//        assertFalse(cell.getEditTextValue() != str);
//        assertFalse(cell.getEditTextValue() == "");
        assertEquals(cell.getEditTextValue() == "", false);
    }

}