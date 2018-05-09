package rrzaniolo.testandroidsantander.network.models.data;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import rrzaniolo.testandroidsantander.network.models.response.CellResponse;

/**
 * This class is a implementation of the email cell.
 * */
public abstract class FieldCell extends Cell{

    //region --- Variable
    private Double typeField;
    public Double getTypeField() {
        return typeField;
    }
    public void setTypeField(Double typeField) {
        this.typeField = typeField;
    }
    //endregion

    //region --- Constructor
    public FieldCell(CellResponse cellResponse) {
        super(cellResponse);
    }
    //endregion

    //region --- Abstract Method
    public abstract Boolean validateAnswer(String answer);
    //endregion
}
