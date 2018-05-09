package rrzaniolo.testandroidsantander.network.models.ui;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/8/2018.
 * All rights reserved.
 */

import android.view.ViewGroup;
import android.widget.TextView;

import rrzaniolo.testandroidsantander.R;
import rrzaniolo.testandroidsantander.network.models.data.Cell;
import rrzaniolo.testandroidsantander.network.models.ui.base.CellView;

/**
 * Representation of the CheckBox cell.
 * */
public class InfoView extends CellView {

    //region --- Constructor
    public InfoView(Cell cell, ViewGroup viewGroup) {
        super(cell, viewGroup);

        inflateView();
    }
    //endregion

    @Override
    protected void inflateView() {
        inflateLayout(R.layout.cell_info);

        ((TextView)getView().findViewById(R.id.cInof_tv)).setText(getCell().getMessage());
    }
}
