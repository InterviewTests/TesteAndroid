package com.meteoro.testeandroid.ui.investiment.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class DownInfoViewModel implements ModelType {

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    String data;

    @Override
    public ViewModelType getType() {
        return ViewModelType.DOWN_INFO;
    }
}
