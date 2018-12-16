package com.avanade.santander.fundos.domain.model;

import com.google.gson.annotations.Expose;

public class Info {

    @Expose
    private final String name;
    @Expose
    private final String data;

    public Info(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }
}
