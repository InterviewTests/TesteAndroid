package com.meteoro.testeandroid.ui.investiment.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class FundInfoViewModel {

    @Getter
    @Setter
    String fund;

    @Getter
    @Setter
    String cdi;
}
