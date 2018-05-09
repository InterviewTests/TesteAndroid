package rrzaniolo.testandroidsantander.network.contact.models.data;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import java.util.regex.Pattern;

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
        Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        );

        return EMAIL_ADDRESS_PATTERN.matcher(answer).matches();
    }
    //endregion
}
