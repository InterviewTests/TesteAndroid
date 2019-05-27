package com.example.alessandrofsouza.santanderapp.service;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ApiServiceTest {

    private ApiService apiService;

    @Test
    public void correct_api_received() {
        assertTrue(ApiService.BASE_URL.contentEquals("https://floating-mountain-50292.herokuapp.com"));
    }

    @Test
    public void wrong_api_received() {
        assertFalse(!ApiService.BASE_URL.contentEquals("https://floating-mountain-50292.herokuapp.com"));
    }
}