package rrzaniolo.testandroidsantander.main.contact.Custom;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/8/2018.
 * All rights reserved.
 */

import java.util.ArrayList;
import java.util.List;

import rrzaniolo.testandroidsantander.network.models.data.Cell;
import rrzaniolo.testandroidsantander.network.models.data.FieldCell;
import rrzaniolo.testandroidsantander.network.models.ui.ButtonView;
import rrzaniolo.testandroidsantander.network.models.ui.CheckBoxView;
import rrzaniolo.testandroidsantander.network.models.ui.InfoView;
import rrzaniolo.testandroidsantander.network.models.ui.TextFieldView;
import rrzaniolo.testandroidsantander.network.models.ui.base.CellView;

/**
 * Class responsible for population a ContractLayout with a list of Cells.
 * */
public class ContractViewBuilder {

    //region --- Variables
    private ContactLayout contactLayout;
    public ContactLayout getContactLayout() {
        return contactLayout;
    }
    public void setContactLayout(ContactLayout contactLayout) {
        this.contactLayout = contactLayout;
    }
    //endregion

    //region --- Constructors
    public ContractViewBuilder(ContactLayout contactLayout) {
        setContactLayout(contactLayout);
    }
    //endregion

    //region --- Private Methods
    private CellView getCellView(Cell cell){
        if(cell.getType() == Cell.CELL_TYPE_FIELD)
            return new TextFieldView((FieldCell) cell, getContactLayout());
        if(cell.getType() == Cell.CELL_TYPE_TEXT)
            return new InfoView(cell, getContactLayout());
        if(cell.getType() == Cell.CELL_TYPE_CHECKBOX)
            return new CheckBoxView(cell, getContactLayout());
        if(cell.getType() == Cell.CELL_TYPE_BUTTON)
            return new ButtonView(cell, getContactLayout());

        return new InfoView(cell, getContactLayout());
    }
    //endregion

    //region --- Public Methods
    public List<CellView> processContractCells(List<Cell> cellList){
        List<CellView> cellViewList = new ArrayList<>();

        for (Cell cell : cellList) {
            cellViewList.add(getCellView(cell));
        }

        return cellViewList;
    }
    //endregion
}
