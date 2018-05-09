package rrzaniolo.testandroidsantander.network.models.ui.base;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/8/2018.
 * All rights reserved.
 */

import android.view.ViewGroup;

import rrzaniolo.testandroidsantander.network.models.data.FieldCell;

/***
 * Representation of Cell that requires some sort of Validation.
 * */
public abstract class FieldCellView extends CellView{

    //region --- Constructor
    public FieldCellView(FieldCell cell, ViewGroup viewGroup) {
        super(cell, viewGroup);
    }
    //endregion

    //region --- Methods
    public abstract Boolean isValidAnswer();

    public abstract void showError();
    public abstract void hideError();

    public abstract void clearField();
    //endregion
}
