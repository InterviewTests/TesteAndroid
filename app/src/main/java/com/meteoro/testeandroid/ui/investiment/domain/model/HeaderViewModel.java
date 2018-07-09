package com.meteoro.testeandroid.ui.investiment.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class HeaderViewModel implements ModelType {

    @Getter
    @Setter
    String title;

    @Getter
    @Setter
    String fundName;

    @Getter
    @Setter
    String whatIs;

    @Getter
    @Setter
    String definition;

    @Getter
    @Setter
    String riskTitle;

    @Getter
    @Setter
    int risk;

    @Getter
    @Setter
    String infoTitle;

    @Getter
    @Setter
    MoreInfoViewModel moreInfoViewModel;

    @Override
    public ViewModelType getType() {
        return ViewModelType.HEADER;
    }
}
