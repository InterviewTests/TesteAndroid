package com.example.alessandrofsouza.santanderapp.data.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApiServiceTest {

    @Test
    public void correct_api_received() {
        assertTrue(ApiService.ServiceApi.BASE_URL.contentEquals("https://floating-mountain-50292.herokuapp.com"));
    }

    @Test
    public void wrong_api_received() {
        assertFalse(!ApiService.ServiceApi.BASE_URL.contentEquals("https://floating-mountain-50292.herokuapp.com"));
    }
}