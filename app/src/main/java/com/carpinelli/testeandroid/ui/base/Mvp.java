package com.carpinelli.testeandroid.ui.base;

public interface Mvp {

    interface Presenter<V> {
        void onAttach(V mvpView);
    }

    interface View {

    }

}
