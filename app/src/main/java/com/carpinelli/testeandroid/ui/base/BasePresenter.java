package com.carpinelli.testeandroid.ui.base;

public class BasePresenter<V extends Mvp> implements Mvp.Presenter<V> {

    private V mMvpView;

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    public V getmMvpView() {
        return mMvpView;
    }

}
