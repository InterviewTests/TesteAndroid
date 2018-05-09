package rrzaniolo.testandroidsantander.network.contact.models.data;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import rrzaniolo.testandroidsantander.network.contact.models.response.CellResponse;

/**
 * This class is a implementation of the email cell.
 * */
public class EmailCell extends FieldCell{

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
    public EmailCell(CellResponse cellResponse) {
        super(cellResponse);
        this.typeField = TYPE_FIELD_EMAIL;
    }
    //endregion

    //region --- Field Cell Methods
    @Override
    public Boolean validateAnswer(String answer) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(answer).matches();
    }
    //endregion
}
