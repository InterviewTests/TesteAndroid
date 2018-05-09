package rrzaniolo.testandroidsantander.network.investment.models;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/9/2018.
 * All rights reserved.
 */

import com.google.gson.annotations.SerializedName;

/**
 * Model for basic information.
 * */
public class BaseInfo {
    //region --- Variables
    @SerializedName("name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("data")
    private String data;
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    //endregion

    //region --- Constructors
    public BaseInfo() { }

    public BaseInfo(String name, String data) {
        this.name = name;
        this.data = data;
    }
    //endregion
}
