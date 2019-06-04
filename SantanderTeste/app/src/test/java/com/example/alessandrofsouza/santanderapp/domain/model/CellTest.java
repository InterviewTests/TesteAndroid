package com.example.alessandrofsouza.santanderapp.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CellTest {

    int i;
    String str;
    boolean bool;

    Cell cell;

    @Before
    public void init() {
        cell = new Cell();
    }

    @Test
    public void id_ReturnsTrue() {
        assertTrue(cell.getId() == i);
    }

    @Test
    public void id_ReturnsFalse() {
        assertFalse(cell.getId() != i);
    }

    @Test
    public void type_ReturnsTrue() {
        assertTrue(cell.getType() == i);
    }

    @Test
    public void type_ReturnsFalse() {
        assertFalse(cell.getType() != i);
    }

    @Test
    public void message_ReturnsTrue() {
        assertTrue(cell.getMessage() == str);
    }

    @Test
    public void message_ReturnsFalse() {
        assertFalse(cell.getMessage() != str);
        assertFalse(cell.getMessage() == "");
    }

    @Test
    public void typeField_ReturnsTrue() {
        assertTrue(cell.getTypefield() == str);
    }

    @Test
    public void typeField_ReturnsFalse() {
        assertFalse(cell.getTypefield() != str);
        assertFalse(cell.getTypefield() == "");
    }

    @Test
    public void hidden_ReturnsFalse() {
        assertFalse(cell.isHidden() == !bool);
    }

    @Test
    public void topSpacing_ReturnsTrue() {
        assertTrue(cell.getTopSpacing() == i);
    }

    @Test
    public void topSpacing_ReturnsFalse() {
        assertFalse(cell.getTopSpacing() != i);
    }


    @Test
    public void show_ReturnsTrue() {
        assertTrue(cell.getShow() == i);
    }

    @Test
    public void show_ReturnsFalse() {
        assertFalse(cell.getShow() != i);
    }

    @Test
    public void required_ReturnsFalse() {
        assertFalse(cell.isRequired() == !bool);
    }

    @Test
    public void getEditTextValue_ReturnsTrue() {
        assertTrue(cell.getEditTextValue() == str);
    }

    @Test
    public void getEditTextValue_ReturnsFalse() {
        assertFalse(cell.getEditTextValue() != str);
        assertFalse(cell.getEditTextValue() == "");
    }

}