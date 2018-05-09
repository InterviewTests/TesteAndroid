package rrzaniolo.testandroidsantander.network.models.ui.base;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/8/2018.
 * All rights reserved.
 */

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import rrzaniolo.testandroidsantander.network.models.data.Cell;

/***
 * View representation of the Cell model.
 * */
public abstract class CellView {

    //region --- Variables
    private Cell cell;
    public Cell getCell() {
        return cell;
    }
    public void setCell(Cell cell) {
        this.cell = cell;
    }

    private View view;
    public View getView() {
        return view;
    }
    public void setView(View view) {
        this.view = view;
    }

    private ViewGroup viewGroup;
    public ViewGroup getViewGroup() {
        return viewGroup;
    }
    public void setViewGroup(ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
    }

    private OnShowView onShowView;
    public OnShowView getOnShowView() {
        return onShowView;
    }
    public void setOnShowView(OnShowView onShowView) {
        if(getCell() != null && getCell().getShow() != -1)
            this.onShowView = onShowView;
    }
    //endregion

    //region --- Constructor
    public CellView(Cell cell, ViewGroup viewGroup) {
        setCell(cell);
        setViewGroup(viewGroup);
    }
    //endregion

    //region --- Protected Methods
    protected void inflateLayout(@LayoutRes int layoutId){
        setView(LayoutInflater
                .from(getViewGroup().getContext())
                .inflate(layoutId, getViewGroup(), false)
        );

        setMargins();

        if(getCell().getHidden())
            getView().setVisibility(View.GONE);
    }

    //region --- Private Methods
    private void setMargins(){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getView().getLayoutParams();
        params.setMargins(0, getCell().getTopSpacing().intValue(), 0,0 );

        getView().setLayoutParams(params);
    }
    //endregion

    //region --- Methods
    protected abstract void inflateView();
    //endregion
}
