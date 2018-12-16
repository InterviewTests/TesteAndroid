package com.avanade.santander.fundos.domain.model;

import com.google.gson.annotations.Expose;

public class More {

    @Expose
    private final String fund;
    @Expose
    private final String CDI;

    public More(String fund, String CDI) {
        this.fund = fund;
        this.CDI = CDI;
    }

    public String getFund() {
        return fund;
    }

    public String getCDI() {
        return CDI;
    }
}
