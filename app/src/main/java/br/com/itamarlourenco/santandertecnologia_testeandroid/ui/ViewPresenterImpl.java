package br.com.itamarlourenco.santandertecnologia_testeandroid.ui;

import br.com.itamarlourenco.santandertecnologia_testeandroid.app.ViewContract;

public class ViewPresenterImpl<T> implements ViewContract.presenter, ViewContract.GetIntractors.OnFinishedListener<T> {

    private ViewContract.ViewActions viewActions;
    private ViewContract.GetIntractors getIntractors;

    public ViewPresenterImpl(ViewContract.ViewActions viewActions, ViewContract.GetIntractors getIntractors) {
        this.viewActions = viewActions;
        this.getIntractors = getIntractors;
    }


    @Override
    public void onFinished(T data) {
        if(viewActions != null){
            viewActions.setDataToRecyclerView(data);
            viewActions.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(viewActions != null){
            viewActions.onResponseFailure(t);
            viewActions.hideProgress();
        }
    }

    @Override
    public void onDestroy() {
        viewActions = null;
    }

    @Override
    public void onRefreshButtonClick() {
        if(viewActions != null){
            viewActions.showProgress();
        }
        getIntractors.getArrayList(this);
    }

    @Override
    public void requestData() {
        getIntractors.getArrayList(this);
    }
}
