package com.meteoro.testeandroid.ui.contact.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class CheckboxViewModel implements CellsType {

    @Getter
    @Setter
    int id;

    @Getter
    @Setter
    String message;

    @Override
    public CellsModelType getType() {
        return CellsModelType.CHECKBOCK;
    }
}
