package com.meteoro.testeandroid.core.data.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class Cell {

    @Getter
    int id;

    @Getter
    int type;

    @Getter
    String message;

    @Getter
    @SerializedName("typefield")
    String typeField;

    @Getter
    boolean hidden;

    @Getter
    int topSpacing;

    @Getter
    int show;

    @Getter
    boolean required;
}
