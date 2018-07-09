package com.meteoro.testeandroid.ui.investiment.domain.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class ScreenViewModel {

    @Getter
    @Setter
    List<ModelType> modelTypeList;
}
