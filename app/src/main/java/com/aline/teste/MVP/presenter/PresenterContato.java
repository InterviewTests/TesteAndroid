package com.aline.teste.MVP.presenter;

import com.aline.teste.MVP.MVP;
import com.aline.teste.MVP.model.ModelContato;
import com.aline.teste.Models.Cells;
import com.aline.teste.eventbus.EventContato;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


public class PresenterContato implements MVP.PresenterCont {

    private List<Cells> cellsList = new ArrayList<>();

    private MVP.ModelCont model;

    public PresenterContato(){
        model = new ModelContato(this);
    }

    @Override
    public void callNetworkContato() {
        model.callRetrofitContato();
    }

    @Override
    public void updateCells(List<Cells> cellslist) {
        this.cellsList = cellslist;
        EventBus.getDefault().post(new EventContato(cellslist));
    }
}
