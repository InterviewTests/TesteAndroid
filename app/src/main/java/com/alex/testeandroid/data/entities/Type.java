package com.alex.testeandroid.data.entities;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Alex on 27/08/18.
 */
public class Type {

    //region CONSTANTS
    public static final int FIELD = 1, TEXT = 2, IMAGE = 3, CHECK_BOX = 4, SEND = 5;
    //endregion

    //region FIELDS
    private int id;
    //endregion

    //region CONSTRUCT
    public Type(int id) {
        this.id = id;
    }
    //endregion

    //region PROPERTIES
    public int getId() {
        return id;
    }
    //endregion

    //region INTERFACES
    @IntDef({FIELD, TEXT, IMAGE, CHECK_BOX, SEND})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TypeCell {
    }
    //endregion
}
