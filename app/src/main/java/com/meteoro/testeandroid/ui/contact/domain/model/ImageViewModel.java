package com.meteoro.testeandroid.ui.contact.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class ImageViewModel implements CellsType {

    @Setter
    @Getter
    int id;

    @Setter
    @Getter
    String message;

    @Getter
    @Setter
    int topSpacing;

    @Override
    public CellsModelType getType() {
        return CellsModelType.IMAGE;
    }
}
