package rrzaniolo.testandroidsantander.network.helper;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import java.util.ArrayList;
import java.util.List;

import rrzaniolo.testandroidsantander.network.models.response.CellResponse;
import rrzaniolo.testandroidsantander.network.models.response.GetCellsResponse;

public class CellMaper {

    public static List<CellResponse> map(GetCellsResponse getCellsResponse){
        List<CellResponse> mappedCells = new ArrayList<>();

        for (CellResponse cellResponse : getCellsResponse.getCells()) {

        }

        return mappedCells;
    }
}
