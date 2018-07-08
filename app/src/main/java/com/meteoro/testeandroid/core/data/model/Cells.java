package com.meteoro.testeandroid.core.data.model;

import java.util.List;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true, chain = true)
public class Cells {

    @Getter
    List<Cell> cells;
}
