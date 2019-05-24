package com.example.alessandrofsouza.santanderapp.adapter;

import com.example.alessandrofsouza.santanderapp.R;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListaCellAdapterTest {

    @Mock
    ListaCellAdapter cellAdapter = mock(ListaCellAdapter.class);

    @Mock
    ListaCellAdapter.ViewHolder viewHolder;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        when(cellAdapter.validateName()).thenReturn(false);
        when(cellAdapter.validateEmail()).thenReturn(false);
        when(cellAdapter.validatePhone()).thenReturn(false);
        when(cellAdapter.MAX_SIZE).thenReturn(anyInt());
    }


    @Test(expected = IllegalStateException.class)
    public void givenMethodIsConfiguredToThrowException_whenCallingMethod_thenExceptionIsThrown() {
        when(cellAdapter.MAX_SIZE).thenThrow(IllegalStateException.class);
    }


}