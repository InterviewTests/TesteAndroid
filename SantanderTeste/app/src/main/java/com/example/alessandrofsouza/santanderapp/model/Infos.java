package com.example.alessandrofsouza.santanderapp.model;

import com.google.gson.annotations.Expose;

public class Infos {

    @Expose
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Expose
    public String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }



    public Infos(String sName, String sData) {
        name = sName;
        data = sData;
    }
}
