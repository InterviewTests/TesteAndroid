package com.carpinelli.testeandroid.model;

import java.io.Serializable;

public class Info implements Serializable {

    private String name;
    private String data;

    public Info() {
    }

    public Info(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public Info(String name) {

    }

    public String getData() {
        return data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
