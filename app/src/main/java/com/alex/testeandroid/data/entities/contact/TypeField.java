package com.alex.testeandroid.data.entities.contact;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Alex on 27/08/18.
 */
public class TypeField {

    //region CONSTANTS
    public static final int TEXT = 1, TEL_NUMBER = 2, EMAIL = 3;
    public static final String TEXT_DESC = "text", TEL_NUMBER_DESC = "telnumber", EMAIL_DESC = "email";
    //endregion

    //region FIELDS
    private int id;
    //endregion

    //region CONSTRUCT
    public TypeField(@TypeFieldCell int id) {
        this.id = id;
    }

    public TypeField(@TypeFieldDescCell String desc) {
        if (desc.equals("text")) {
            this.id = TEXT;
        } else if (desc.equals("telnumber")) {
            this.id = TEL_NUMBER;
        } else {
            this.id = EMAIL;
        }
    }
    //endregion

    //region PROPERTIES
    public int getId() {
        return id;
    }
    //endregion

    //region INTERFACES
    @IntDef({TEXT, TEL_NUMBER, EMAIL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TypeFieldCell {
    }

    @StringDef({TEXT_DESC, TEL_NUMBER_DESC, EMAIL_DESC})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TypeFieldDescCell {
    }
    //endregion
}
