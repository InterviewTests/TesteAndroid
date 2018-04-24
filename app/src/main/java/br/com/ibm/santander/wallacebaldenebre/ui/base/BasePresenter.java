package br.com.ibm.santander.wallacebaldenebre.ui.base;

public class BasePresenter<V extends MvpContract.MvpView> implements MvpContract.MvpPresenter<V> {

    private V mMvpView;

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    public V getMvpView() {
        return mMvpView;
    }
}