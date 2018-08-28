package com.alex.testeandroid.data.entities;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Alex on 27/08/18.
 */
public class TypeField {

    //region CONSTANTS
    public static final int TEXT = 1, TEL_NUMBER = 2, EMAIL = 3;
    //endregion

    //region FIELDS
    private int id;
    //endregion

    //region CONSTRUCT
    public TypeField(int id) {
        this.id = id;
    }

    public TypeField(String desc) {
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
    //endregion
}
