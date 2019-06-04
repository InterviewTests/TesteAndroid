package com.example.alessandrofsouza.santanderapp.domain.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InfosTest {

    String str;
    Infos info;

    @Before
    public void init() {
        info = new Infos(str, str, str);
    }

    @Test
    public void title_ReturnsTrue() {
        assertTrue(info.getTitle() == str);
    }

    @Test
    public void title_ReturnsFalse() {
        assertFalse(info.getTitle() != str);
        assertFalse(info.getTitle() == "");
    }

    @Test
    public void name_ReturnsTrue() {
        assertTrue(info.getName() == str);
    }

    @Test
    public void name_ReturnsFalse() {
        assertFalse(info.getName() != str);
        assertFalse(info.getName() == "");
    }

    @Test
    public void data_ReturnsTrue() {
        assertTrue(info.getData() == str);
    }

    @Test
    public void data_ReturnsFalse() {
        assertFalse(info.getData() != str);
        assertFalse(info.getData() == "");
    }

}