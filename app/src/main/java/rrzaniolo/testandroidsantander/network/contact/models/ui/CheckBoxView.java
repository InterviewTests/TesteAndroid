package rrzaniolo.testandroidsantander.network.contact.models.ui;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/8/2018.
 * All rights reserved.
 */

import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import rrzaniolo.testandroidsantander.R;
import rrzaniolo.testandroidsantander.network.contact.models.data.Cell;
import rrzaniolo.testandroidsantander.network.contact.models.ui.base.CellView;

/**
 * Representation of the CheckBox cell.
 * */
public class CheckBoxView extends CellView {

    //region --- Constructor
    public CheckBoxView(Cell cell, ViewGroup viewGroup) {
        super(cell, viewGroup);

        inflateView();
    }
    //endregion

    @Override
    protected void inflateView() {
        inflateLayout(R.layout.cell_checkbox);

        ((CheckBox)getView().findViewById(R.id.cCehckbox_cb)).setText(getCell().getMessage());
        ((CheckBox)getView().findViewById(R.id.cCehckbox_cb)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(getOnShowView() != null) {
                    if (isChecked)
                        getOnShowView().showView(getCell().getShow());
                    else
                        getOnShowView().hideView(getCell().getShow());
                }

            }
        });
    }
}
