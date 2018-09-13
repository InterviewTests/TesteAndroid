package com.nataliafavero.santander.data.model;

import com.google.gson.annotations.Expose;

public class Info {
    @Expose
    private String name;
    @Expose
    private String data;

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }
}
