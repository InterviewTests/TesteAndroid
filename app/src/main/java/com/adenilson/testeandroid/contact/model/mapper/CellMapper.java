package com.adenilson.testeandroid.contact.model.mapper;

import com.adenilson.testeandroid.contact.model.Cell;
import com.adenilson.testeandroid.networking.webservices.contact.CellResponse;
import com.adenilson.testeandroid.networking.webservices.contact.CellsResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class CellMapper {

    public List<Cell> mapList(CellsResponse cellsResponse){
        ArrayList<Cell> cells = new ArrayList<>();
        List<CellResponse> cellResponses = cellsResponse.getCells();
        for(CellResponse response: cellResponses){
            cells.add(new Cell(response.getMessage(), response.getType(),response.getTypeField(), response.isHidden(), (int) response.getTopSpacing(), response.isRequired()));
        }

        return cells;
    }


}
