package rrzaniolo.testandroidsantander.network.models.data;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import android.util.Patterns;

import rrzaniolo.testandroidsantander.network.models.response.CellResponse;

/**
 * This class is an implementation of the phone cell.
 * */
public class PhoneCell extends FieldCell {

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
    public PhoneCell(CellResponse cellResponse) {
        super(cellResponse);
        this.typeField = TYPE_FIELD_PHONE;
    }
    //endregion

    //region --- Field Cell Methods
    @Override
    public Boolean validateAnswer(String answer) {
        return Patterns.PHONE.matcher(answer).matches();
    }
    //endregion
}
