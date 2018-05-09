package rrzaniolo.testandroidsantander.contact.helper;/*
 * Created by rrzaniolo on 09/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rrzaniolo.testandroidsantander.main.contact.helper.CellMaper;
import rrzaniolo.testandroidsantander.network.contact.models.data.Cell;
import rrzaniolo.testandroidsantander.network.contact.models.response.CellResponse;
import rrzaniolo.testandroidsantander.network.contact.models.response.GetCellsResponse;

import static org.junit.Assert.assertEquals;

public class CellMaperTest {

    private GetCellsResponse getCellsResponse;

    private Integer ID          = 1;
    private Integer SHOW        = 1;
    private Integer TYPE        = 1;
    private Boolean HIDDEN      = false;
    private Boolean REQUIRED    = true;
    private String MESSAGE      = "message";
    private Double TOP_SPACING  = 35.0;
    private Object TYPE_FIELD   = null;

    @Before
    public void init(){
        getCellsResponse = new GetCellsResponse();

        List<CellResponse> cellResponses = new ArrayList<>();
        CellResponse cellResponse = new CellResponse();

        cellResponses.add(cellResponse);
        cellResponse.setId(ID);
        cellResponse.setShow(SHOW);
        cellResponse.setType(TYPE);
        cellResponse.setHidden(HIDDEN);
        cellResponse.setRequired(REQUIRED);
        cellResponse.setMessage(MESSAGE);
        cellResponse.setTopSpacing(TOP_SPACING);
        cellResponse.setTypeField(TYPE_FIELD);

        getCellsResponse.setCells(cellResponses);
    }

    @Test
    public void map(){
        assertEquals(getCellsResponse.getCells().size(), 1);

        List<Cell> cellList = CellMaper.map(getCellsResponse);

        assertEquals(cellList.size(), getCellsResponse.getCells().size());

        Cell cell = cellList.get(0);

        assertEquals(cell.getId()           , ID);
        assertEquals(cell.getShow()         , SHOW);
        assertEquals(cell.getType()         , TYPE);
        assertEquals(cell.getHidden()       , HIDDEN);
        assertEquals(cell.getRequired()     , REQUIRED);
        assertEquals(cell.getMessage()      , MESSAGE);
        assertEquals(cell.getTopSpacing()   , TOP_SPACING);
    }
}
