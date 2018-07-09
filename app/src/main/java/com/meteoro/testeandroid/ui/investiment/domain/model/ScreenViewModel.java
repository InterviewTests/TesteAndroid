package com.meteoro.testeandroid.ui.investiment.domain.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class ScreenViewModel {

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

    @Getter
    @Setter
    List<InfoViewModel> infoViewModels;

    @Getter
    @Setter
    List<DownInfoViewModel> downInfoViewModels;
}
