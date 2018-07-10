package com.meteoro.testeandroid.ui.investiment.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class MoreInfoViewModel {

    @Getter
    @Setter
    FundInfoViewModel month;

    @Getter
    @Setter
    FundInfoViewModel year;

    @Getter
    @Setter
    FundInfoViewModel months12;
}
