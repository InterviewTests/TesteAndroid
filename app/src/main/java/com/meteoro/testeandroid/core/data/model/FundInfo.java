package com.meteoro.testeandroid.core.data.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class FundInfo {

    @Getter
    double fund;

    @Getter
    @SerializedName("CDI")
    double cdi;
}
