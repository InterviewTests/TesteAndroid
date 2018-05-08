package rrzaniolo.testandroidsantander.network.models.ui;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import rrzaniolo.testandroidsantander.network.models.response.CellResponse;

/***
 * This class is an implementation of the text cell.
 * */
public class TextCell extends Cell{
    //region --- Variable
    private int typeField;
    public int getTypeField() {
        return typeField;
    }
    public void setTypeField(int typeField) {
        this.typeField = typeField;
    }
    //endregion

    //region --- Constructor
    public TextCell(CellResponse cellResponse) {
        super(cellResponse);
        this.typeField = TYPE_FIELD_TEXT;
    }
    //endregion
}
