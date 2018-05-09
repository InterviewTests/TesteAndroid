package rrzaniolo.testandroidsantander.network.contact.helper;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import java.util.ArrayList;
import java.util.List;

import rrzaniolo.testandroidsantander.network.contact.models.response.CellResponse;
import rrzaniolo.testandroidsantander.network.contact.models.response.GetCellsResponse;
import rrzaniolo.testandroidsantander.network.contact.models.data.Cell;
import rrzaniolo.testandroidsantander.network.contact.models.data.EmailCell;
import rrzaniolo.testandroidsantander.network.contact.models.data.PhoneCell;
import rrzaniolo.testandroidsantander.network.contact.models.data.TextCell;
import rrzaniolo.testandroidsantander.utils.Constants;

public class CellMaper {

    public static List<Cell> map(GetCellsResponse getCellsResponse){
        List<Cell> mappedCells = new ArrayList<>();

        for (CellResponse cellResponse : getCellsResponse.getCells()) {
            mappedCells.add(cellMap(cellResponse));
        }

        return mappedCells;
    }

    private static Cell cellMap(CellResponse cellResponse){
        if(cellResponse.getTypeField() instanceof  Double){
            if(cellResponse.getTypeField().equals(Cell.TYPE_FIELD_TEXT))
                return new TextCell(cellResponse);

            if(cellResponse.getTypeField().equals(Cell.TYPE_FIELD_PHONE))
                return new PhoneCell(cellResponse);

            if(cellResponse.getTypeField().equals(Cell.TYPE_FIELD_EMAIL))
                return new EmailCell(cellResponse);

        }else if(cellResponse.getTypeField() instanceof String){
            if(Constants.PHONE_TYPE_FIELD.equals(cellResponse.getTypeField()))
                return new PhoneCell(cellResponse);
        }

        return new Cell(cellResponse);
    }
}
