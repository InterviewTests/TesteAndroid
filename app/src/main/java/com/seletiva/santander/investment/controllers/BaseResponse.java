package com.seletiva.santander.investment.controllers;

public interface BaseResponse {
    void onConnectionFailure();
    void onRequestFailure(int stringMessageId);
}
