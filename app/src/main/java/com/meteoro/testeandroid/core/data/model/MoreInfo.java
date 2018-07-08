package com.meteoro.testeandroid.core.data.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class MoreInfo {

    @Getter
    FundInfo month;

    @Getter
    FundInfo year;

    @Getter
    @SerializedName("12months")
    FundInfo months12;
}
