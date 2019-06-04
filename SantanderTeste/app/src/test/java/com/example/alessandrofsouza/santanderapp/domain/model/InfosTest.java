package com.example.alessandrofsouza.santanderapp.domain.model;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class InfosTest {

//    String str;
    Infos info;

    @Before
    public void init() {
        info = new Infos("string", "string", "string");
    }

    @Test
    public void title_ReturnsTrue() {
//        assertTrue(info.getTitle() == str);
        assertThat(info.getTitle(), is("string"));
    }


    @Test
    public void name_ReturnsTrue() {
//        assertTrue(info.getName() == str);
        assertThat(info.getName(), is("string"));
    }


    @Test
    public void data_ReturnsTrue() {
//        assertTrue(info.getData() == str);
        assertThat(info.getData(), is("string"));
    }


}