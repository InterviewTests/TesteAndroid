package com.nataliafavero.santander.data.model;

import com.google.gson.annotations.Expose;

public class MoreInfoDetail {
    @Expose
    private Double fund;
    @Expose
    private Double CDI;

    public Double getFund() {
        return fund;
    }

    public Double getCDI() {
        return CDI;
    }
}
