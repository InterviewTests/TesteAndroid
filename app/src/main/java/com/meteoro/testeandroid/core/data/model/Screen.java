package com.meteoro.testeandroid.core.data.model;

import java.util.List;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class Screen {

    @Getter
    String title;

    @Getter
    String fundName;

    @Getter
    String whatIs;

    @Getter
    String definition;

    @Getter
    String riskTitle;

    @Getter
    int risk;
    String infoTitle;

    @Getter
    MoreInfo moreInfo;

    @Getter
    List<Info> info;

    @Getter
    List<DownInfo> downInfo;
}
