package rrzaniolo.testandroidsantander.network.contact.models.ui;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/8/2018.
 * All rights reserved.
 */

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import rrzaniolo.testandroidsantander.R;
import rrzaniolo.testandroidsantander.network.contact.models.data.Cell;
import rrzaniolo.testandroidsantander.network.contact.models.ui.base.CellView;

/**
 * Representation of the CheckBox cell.
 * */
public class ButtonView extends CellView {

    //region --- Constructor
    public ButtonView(Cell cell, ViewGroup viewGroup) {
        super(cell, viewGroup);

        inflateView();
    }
    //endregion

    @Override
    protected void inflateView() {
        inflateLayout(R.layout.cell_btn);

        ((Button)getView().findViewById(R.id.cButton_btn)).setText(getCell().getMessage());
    }

    public void setOnClickListener(View.OnClickListener listener){
        getView().findViewById(R.id.cButton_btn).setOnClickListener(listener);
    }
}
