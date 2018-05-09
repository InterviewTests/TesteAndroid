package rrzaniolo.testandroidsantander.network.contact.models.data;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import java.util.regex.Pattern;

import rrzaniolo.testandroidsantander.network.contact.models.response.CellResponse;

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
        Pattern PHONE
                = Pattern.compile(                      // sdd = space, dot, or dash
                "(\\+[0-9]+[\\- \\.]*)?"                // +<digits><sdd>*
                        + "(\\([0-9]+\\)[\\- \\.]*)?"   // (<digits>)<sdd>*
                        + "([0-9][0-9\\- \\.]+[0-9])"); // <digit><digit|sdd>+<digit>


        return PHONE.matcher(answer).matches();
    }
    //endregion
}
