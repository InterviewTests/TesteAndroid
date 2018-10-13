package br.com.itamarlourenco.santandertecnologia_testeandroid.ui;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.app.MainContract;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;

public class MainPresenterImpl implements MainContract.presenter, MainContract.GetCellsIntractors.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetCellsIntractors getCellsIntractors;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetCellsIntractors getCellsIntractors) {
        this.mainView = mainView;
        this.getCellsIntractors = getCellsIntractors;
    }

    @Override
    public void onFinished(ArrayList<Cell> cellArrayList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(cellArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onRefreshButtonClick() {
        if(mainView != null){
            mainView.showProgress();
        }
        getCellsIntractors.getCellArrayList(this);
    }

    @Override
    public void requestData() {
        getCellsIntractors.getCellArrayList(this);
    }
}
