package com.carpinelli.testeandroid.di;

public interface Mvp {

    interface Presenter<V> {
        void onAttach(V mvpView);
    }

    interface View {

    }

}
