package com.meteoro.testeandroid.ui.contact.domain.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class CellsViewModel {

    @Getter
    @Setter
    List<CellsType> cellsTypeList;
}
