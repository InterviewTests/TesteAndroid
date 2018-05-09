package rrzaniolo.testandroidsantander.main.contact.Custom;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/8/2018.
 * All rights reserved.
 */

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import rrzaniolo.testandroidsantander.network.models.ui.ButtonView;
import rrzaniolo.testandroidsantander.network.models.ui.base.CellView;
import rrzaniolo.testandroidsantander.network.models.ui.base.FieldCellView;
import rrzaniolo.testandroidsantander.network.models.ui.base.OnShowView;

/**
 * Layout that will represent the Form.
 * */
public class ContactLayout extends LinearLayout implements OnShowView{

    //region --- Variables
    private List<CellView> cellViewList;
    public List<CellView> getCellViewList() {
        return cellViewList;
    }
    public void setCellViewList(List<CellView> cellViewList) {
        this.cellViewList = cellViewList;

        for (CellView cellView : cellViewList) {
            cellView.setOnShowView(ContactLayout.this);
            addView(cellView.getView());
        }

        Log.d("", "");
    }
    //endregion

    //region --- Constructor
    public ContactLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    //endregion

    //region --- Public Methods
    public void setButtonListener(View.OnClickListener onClickListener){
        for (CellView cellView : getCellViewList()) {
            if(cellView instanceof ButtonView)
               ((ButtonView) cellView).setOnClickListener(onClickListener);
        }
    }

    public void clearFields(){
        for (CellView cellView : getCellViewList()) {
            if(cellView instanceof FieldCellView) {
                ((FieldCellView) cellView).hideError();
                ((FieldCellView) cellView).clearField();
            }
        }
    }

    public void showErrors(){
        for (CellView cellView : getCellViewList()) {
            if(cellView instanceof FieldCellView)
                ((FieldCellView) cellView).hideError();
        }
    }

    public void hideErrors(){
        for (CellView cellView : getCellViewList()) {
            if(cellView instanceof FieldCellView)
                ((FieldCellView) cellView).showError();
        }
    }

    public Boolean checkErrors(){
        Boolean hasError = false;
        for (CellView cellView : getCellViewList()) {
            if(cellView instanceof FieldCellView)
                if(!((FieldCellView) cellView).isValidAnswer())
                    hasError = true;
        }

        return hasError;
    }
    //endregion

    //region --- OnShoView Methods
    @Override
    public void showView(int id) {
        for (CellView cellView : getCellViewList()) {
            if(cellView.getCell().getId() == id)
                cellView.getView().setVisibility(VISIBLE);
        }
    }

    @Override
    public void hideView(int id) {
        for (CellView cellView : getCellViewList()) {
            if(cellView.getCell().getId() == id)
                cellView.getView().setVisibility(GONE);
        }
    }
    //endregion
}
